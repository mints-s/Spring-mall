package com.example._A.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example._A.domain.*.command.handler.*.*(..))")
    private void commandHandlers() {}

    @Pointcut("execution(* com.example._A.domain.*.query.handler.*.*(..))")
    private void queryHandlers() {}

    @Pointcut("execution(* com.example._A.domain.*.controller.*.*(..))")
    private void controllerMethods() {}

    /** Command Handler: 실행 시간 + 예외 로깅 */
    @Around("commandHandlers()")
    public Object logCommand(ProceedingJoinPoint pjp) throws Throwable {
        String handler = pjp.getTarget().getClass().getSimpleName();
        String method  = pjp.getSignature().getName();
        String command = pjp.getArgs().length > 0 ? pjp.getArgs()[0].getClass().getSimpleName() : "-";
        long start = System.currentTimeMillis();
        log.info("[COMMAND] {}.{}({}) ▶ START", handler, method, command);
        try {
            Object result = pjp.proceed();
            log.info("[COMMAND] {}.{}({}) ◀ END ({}ms)", handler, method, command, System.currentTimeMillis() - start);
            return result;
        } catch (Exception e) {
            log.error("[COMMAND] {}.{}({}) ✕ ERROR - {}", handler, method, command, e.getMessage());
            throw e;
        }
    }

    /** Query Handler: 실행 시간 로깅 */
    @Around("queryHandlers()")
    public Object logQuery(ProceedingJoinPoint pjp) throws Throwable {
        String handler = pjp.getTarget().getClass().getSimpleName();
        String method  = pjp.getSignature().getName();
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        log.debug("[QUERY]  {}.{}() ({}ms)", handler, method, System.currentTimeMillis() - start);
        return result;
    }

    /** Controller: 진입 로깅 */
    @Before("controllerMethods()")
    public void logController(JoinPoint jp) {
        log.info("[CTRL]   {}.{}()",
                jp.getTarget().getClass().getSimpleName(),
                jp.getSignature().getName());
    }
}
