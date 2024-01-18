package org.example.article;

// POJO - Plain Old Java Object
public class ArticleMem {
    private String title;
    private String content;

    public ArticleMem() {
    }

    public ArticleMem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
