package com.example._A.domain.comment.entity;

import com.example._A.domain.article.entity.Article;
import com.example._A.domain.comment.dto.CommentDto;
import com.example._A.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("fail..wrong comment id.....");
        }
        if (!dto.getArticleId().equals(article.getId())) {
            throw new IllegalArgumentException("fail..wrong article id.....");
        }
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        if (!this.id.equals(dto.getId())) {
            throw new IllegalArgumentException("update fail...wrong id..");
        }
        if (dto.getNickname() != null) this.nickname = dto.getNickname();
        if (dto.getBody() != null) this.body = dto.getBody();
    }
}
