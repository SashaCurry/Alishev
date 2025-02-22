package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }


    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id},
                                  new BeanPropertyRowMapper<>(Person.class))
                           .stream().findAny().orElse(null);
    }
    public Optional<Person> show(int id, String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=? AND person_id <> ?", new Object[]{name, id},
                                  new BeanPropertyRowMapper<>(Person.class))
                           .stream().findAny();
    }


    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (name, year_birth) VALUES (?, ?)", person.getName(), person.getYear_birth());
    }


    public void update(Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, year_birth=? WHERE person_id=?",
                            person.getName(), person.getYear_birth(), person.getPerson_id());
    }
}
