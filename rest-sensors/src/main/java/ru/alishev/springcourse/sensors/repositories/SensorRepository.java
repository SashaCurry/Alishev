package ru.alishev.springcourse.sensors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.sensors.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    boolean existsByNameAndIdNot(String name, int id);

    boolean existsByName(String name);

    Sensor findByName(String name);
}
