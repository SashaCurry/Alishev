package ru.alishev.springcourse.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @NotEmpty
    @Size(min = 8, max = 50, message = "Author should be between 10 and 50 characters")
    private String author;

    @NotEmpty
    @Max(value = 2025, message = "Year of brith should be less than 2025")
    private int year_released;

    private int person_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear_released() {
        return year_released;
    }

    public void setYear_released(int year_released) {
        this.year_released = year_released;
    }
}
