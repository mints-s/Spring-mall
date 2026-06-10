package com.example._A.global.init;

import com.example._A.domain.user.command.handler.UserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserCommandHandler userCommandHandler;

    @EventListener(ApplicationReadyEvent.class)
    public void initAdminUser() {
        userCommandHandler.handleCreateAdmin("admin@shop.com", "admin1234");
    }
}
