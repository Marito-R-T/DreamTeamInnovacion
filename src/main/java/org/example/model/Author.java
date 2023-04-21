package org.example.model;

import java.util.List;

public class Author {

    private String name;
    private Integer citations;
    private List<Article> articles;
    private String id;

    public Author(String id) {
        this.id = id;
    }

    public Author(String name, Integer citations, String id) {
        this.name = name;
        this.citations = citations;
        this.id = id;
    }

    public Author(String name, Integer citations, List<Article> articles, String id) {
        this.name = name;
        this.citations = citations;
        this.articles = articles;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCitations() {
        return citations;
    }

    public void setCitations(Integer citations) {
        this.citations = citations;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getId() {
        return id;
    }
}
