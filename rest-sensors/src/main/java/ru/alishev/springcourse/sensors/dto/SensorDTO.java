package ru.alishev.springcourse.sensors.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @Column(name = "name")
    @NotEmpty(message = "Sensor should be have name")
    @Size(min = 3, max = 30, message = "Sensor name should be between 3 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
