package ru.alishev.springcourse.sensors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.sensors.models.Measurement;
import ru.alishev.springcourse.sensors.models.Sensor;
import ru.alishev.springcourse.sensors.repositories.MeasurementRepository;
import ru.alishev.springcourse.sensors.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    // НЕ ЗАБЫТЬ ВМЕСТО NULL ВЫБРАСЫВАТЬ ИСКЛЮЧЕНИЕ (НЕ ПРИГОДИЛОСЬ, ПОЭТОМУ НЕ РЕАЛИЗОВАНО)
    public Measurement findOne(int id) {
        return measurementRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);

        measurementRepository.save(measurement);
    }

    public long countRaining() {
        return measurementRepository.countByRainingIsTrue();
    }


    private void enrichMeasurement(Measurement measurement) {
        Sensor sensor = measurement.getSensor();

        sensor.setId(sensorRepository.findByName(sensor.getName()).getId());
        measurement.setTime(LocalDateTime.now());
    }
}
