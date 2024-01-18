package com.example.article.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/*
CREATE TABLE article(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT,
    content TEXT,
    writer TEXT
)
*/
//단, @Data는 사용하지 않는다.
@Entity
@Getter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String writer;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public Article(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
