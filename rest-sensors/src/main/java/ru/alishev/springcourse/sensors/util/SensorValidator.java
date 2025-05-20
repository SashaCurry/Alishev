package ru.alishev.springcourse.sensors.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.sensors.models.Sensor;
import ru.alishev.springcourse.sensors.services.SensorService;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorService.existsByNameAndNotId(sensor.getName(), sensor.getId()))
            errors.rejectValue("name", "",
                    "Sensor with name " + sensor.getName() + " already exists");
    }
}
