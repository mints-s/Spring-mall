package com.example._A.domain.comment.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentCommand {
    private Long articleId;
    private String nickname;
    private String body;
}
