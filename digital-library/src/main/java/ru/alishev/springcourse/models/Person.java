package ru.alishev.springcourse.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int person_id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 8, max = 50, message = "Name should be between 8 and 50 characters")
    private String name;

    @Min(value = 1925, message = "Year of brith should be greater than 1925")
    @Max(value = 2020, message = "Year of brith should be less than 2020")
    private int year_birth;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
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
}
