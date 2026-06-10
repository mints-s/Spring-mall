package com.example._A.domain.comment.command.handler;

import com.example._A.domain.article.entity.Article;
import com.example._A.domain.article.repository.ArticleRepository;
import com.example._A.domain.comment.command.CreateCommentCommand;
import com.example._A.domain.comment.command.DeleteCommentCommand;
import com.example._A.domain.comment.command.UpdateCommentCommand;
import com.example._A.domain.comment.dto.CommentDto;
import com.example._A.domain.comment.entity.Comment;
import com.example._A.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CommentCommandHandler {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public CommentDto handle(CreateCommentCommand command) {
        Article article = articleRepository.findById(command.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + command.getArticleId()));
        CommentDto dto = new CommentDto(null, command.getArticleId(), command.getNickname(), command.getBody(), null);
        Comment saved = commentRepository.save(Comment.createComment(dto, article));
        return CommentDto.createCommentDto(saved);
    }

    @Transactional
    public CommentDto handle(UpdateCommentCommand command) {
        Comment target = commentRepository.findById(command.getId())
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다: " + command.getId()));
        target.patch(new CommentDto(command.getId(), null, command.getNickname(), command.getBody(), null));
        return CommentDto.createCommentDto(commentRepository.save(target));
    }

    public CommentDto handle(DeleteCommentCommand command) {
        Comment target = commentRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다: " + command.id()));
        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);
    }
}
