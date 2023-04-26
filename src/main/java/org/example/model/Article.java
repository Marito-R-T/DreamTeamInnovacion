package org.example.model;

import java.time.Year;

public class Article {

    private String title, publication, id;
    private Author author;
    private Year year;
    public Article() {

    }

    public Article(String title, String publication, String id, Author author, Year year) {
        this.title = title;
        this.publication = publication;
        this.id = id;
        this.author = author;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
