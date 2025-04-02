package ru.alishev.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Size(min = 8, max = 50, message = "Author should be between 10 and 50 characters")
    @Column(name = "author")
    private String author;

    @Max(value = 2025, message = "Year of brith should be less than 2025")
    @Column(name = "year_released")
    private int year_released;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book() {}
    public Book(String name, String author, int year_released) {
        this.name = name;
        this.author = author;
        this.year_released = year_released;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year_released=" + year_released +
                ", owner=" + owner +
                '}';
    }
}
