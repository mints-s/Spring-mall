package com.example._A.domain.comment.query.handler;

import com.example._A.domain.comment.dto.CommentDto;
import com.example._A.domain.comment.entity.Comment;
import com.example._A.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentQueryHandler {

    private final CommentRepository commentRepository;

    public List<CommentDto> findByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId).stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }

    public Page<Comment> findAllPaged(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }
}
