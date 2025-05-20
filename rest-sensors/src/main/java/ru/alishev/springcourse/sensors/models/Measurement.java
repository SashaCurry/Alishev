package ru.alishev.springcourse.sensors.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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
    private Sensor sensor;

    @Column(name = "time")
    private LocalDateTime time;

    public Measurement() {}
    public Measurement(int id, float value, boolean raining, Sensor sensor, LocalDateTime time) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                ", time=" + time +
                '}';
    }
}
