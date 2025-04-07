package ru.alishev.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

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
    private int yearReleased;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name="date_assign")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAssign;

    public Book() {}
    public Book(String name, String author, int yearReleased) {
        this.name = name;
        this.author = author;
        this.yearReleased = yearReleased;
    }

    public boolean isDelay() {
        if (dateAssign == null)
            return false;

        return new Date().getTime() - dateAssign.getTime() > 86_400_000;
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

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getDateAssign() {
        return dateAssign;
    }

    public void setDateAssign(Date dateAssign) {
        this.dateAssign = dateAssign;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearReleased=" + yearReleased +
                ", owner=" + owner +
                ", dateAssign=" + dateAssign +
                '}';
    }
}
