package ru.alishev.springcourse.sensors.util;

public class MeasurementNotAddedException extends RuntimeException {
    public MeasurementNotAddedException(String message) {
        super(message);
    }
}
