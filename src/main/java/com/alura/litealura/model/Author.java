package com.alura.litealura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    private Long id;
    private String name;
    private Long birth_year;
    private Long death_year;

    public Author() {
    }

    public Author(Long id, String name, Long birth_year, Long death_year) {
        this.id = id;
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Long birth_year) {
        this.birth_year = birth_year;
    }

    public Long getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Long death_year) {
        this.death_year = death_year;
    }
}
