package com.example._A.domain.comment.controller;

import com.example._A.domain.comment.command.CreateCommentCommand;
import com.example._A.domain.comment.command.DeleteCommentCommand;
import com.example._A.domain.comment.command.UpdateCommentCommand;
import com.example._A.domain.comment.command.handler.CommentCommandHandler;
import com.example._A.domain.comment.dto.CommentDto;
import com.example._A.domain.comment.query.handler.CommentQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentCommandHandler commentCommandHandler;
    private final CommentQueryHandler commentQueryHandler;

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        return ResponseEntity.ok(commentQueryHandler.findByArticleId(articleId));
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto) {
        return ResponseEntity.ok(commentCommandHandler.handle(
                new CreateCommentCommand(articleId, dto.getNickname(), dto.getBody())));
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto) {
        return ResponseEntity.ok(commentCommandHandler.handle(
                new UpdateCommentCommand(id, dto.getNickname(), dto.getBody())));
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(commentCommandHandler.handle(new DeleteCommentCommand(id)));
    }
}
