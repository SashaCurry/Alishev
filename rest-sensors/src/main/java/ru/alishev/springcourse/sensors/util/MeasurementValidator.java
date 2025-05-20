package ru.alishev.springcourse.sensors.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.sensors.models.Measurement;
import ru.alishev.springcourse.sensors.services.SensorService;

@Component
public class MeasurementValidator implements Validator {
    public final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (measurement.getSensor() != null && !sensorService.existsByName(measurement.getSensor().getName()))
            errors.rejectValue("sensor", "", "Sensor doesn't exist");
    }
}
