package com.alura.litealura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private Long id;
    private String title;
    private List<Author> authors;
    private List<String> languages;
    private Long download_count;

    public Book() {
    }

    public Book(Long id, String title, List<Author> authors, List<String> languages, Long download_count) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.download_count = download_count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long bookId) {
        this.id = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Long getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Long download_count) {
        this.download_count = download_count;
    }
}
