package ru.alishev.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }


    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }
    @Transactional(readOnly = true)
    public Person show(String name) {
        Session session = sessionFactory.getCurrentSession();

        Query<Person> query = session.createQuery("FROM Person WHERE name = :name", Person.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }


    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }


    @Transactional
    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(person);
    }


    @Transactional
    public void delete(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(person);
    }
}
