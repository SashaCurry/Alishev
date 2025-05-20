package ru.alishev.springcourse.sensors.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class MeasurementDTO {
    @Column(name = "value")
    @NotNull(message = "Value shouldn't be empty")
    @Max(value = 100, message =  "Value should be less 100")
    @Min(value = -100, message = "Value should be more 100")
    private Float value;

    @Column(name = "raining")
    @NotNull(message = "Raining shouldn't be empty")
    private Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull(message = "Sensor shouldn't be empty")
    private SensorDTO sensor;

    @Column(name = "time")
    private LocalDateTime time;

    public Float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
