package com.example._A.domain.article.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleCommand {
    private Long id;
    private String title;
    private String content;
}
