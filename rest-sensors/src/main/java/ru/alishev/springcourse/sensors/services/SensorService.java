package ru.alishev.springcourse.sensors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.sensors.models.Sensor;
import ru.alishev.springcourse.sensors.repositories.SensorRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    // НЕ ЗАБЫТЬ ВМЕСТО NULL ВЫБРАСЫВАТЬ ИСКЛЮЧЕНИЕ (НЕ ПРИГОДИЛОС, ПОЭТОМУ НЕ РЕАЛИЗОВАНО)
    public Sensor findOne(int id) {
        return sensorRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public boolean existsByNameAndNotId(String name, int id) {
        return sensorRepository.existsByNameAndIdNot(name, id);
    }

    public boolean existsByName(String name) {
        return sensorRepository.existsByName(name);
    }
}
