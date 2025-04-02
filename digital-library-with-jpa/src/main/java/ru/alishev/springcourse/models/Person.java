package ru.alishev.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 8, max = 50, message = "Name should be between 8 and 50 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 1925, message = "Year of brith should be greater than 1925")
    @Max(value = 2020, message = "Year of brith should be less than 2020")
    @Column(name = "year_birth")
    private int year_birth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {}
    public Person(String name, int year_birth) {
        this.name = name;
        this.year_birth = year_birth;
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

    public int getYear_birth() {
        return year_birth;
    }

    public void setYear_birth(int year_birth) {
        this.year_birth = year_birth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year_birth=" + year_birth +
                '}';
    }
}
