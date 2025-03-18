package ru.alishev.springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.alishev.springcourse.models.Director;
import ru.alishev.springcourse.models.Movie;

import java.util.*;


public class App {
    public static void main( String[] args ) {
//        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
//
//        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
//            Session session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//
//            Person sashaCurry = session.get(Person.class, 4);
//
//            session.remove(sashaCurry);
//            sashaCurry.getItems().forEach(i -> i.setOwner(null));
//
//            session.getTransaction().commit();
//        }
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class).addAnnotatedClass(Movie.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // 1) Получить режиссёра, а потом его фильмы
            Director director = session.get(Director.class, new Random().nextInt(1, 7));
            List<Movie> movies = director.getMovies();
            for (Movie movie : movies) {
                System.out.println(movie);
            }

            // 2) Получить фильм, а потом его рвежиссёра
            Movie movie = session.get(Movie.class, new Random().nextInt(1, 12));
            System.out.println(movie);
            Director director = director.getDirector();
            System.out.println(director);

            // 3) Добавить ещё один фильм для режжисёра
            Director christopherNolan = session.get(Director.class, 6);
            Movie newMovie = new Movie("Tenet", 2020, christopherNolan);
            session.persist(newMovie);
            christopherNolan.getMovies().add(newMovie);

            // 4) Создать нового режиссёра и фильм и связать сущности
            Director seanBaker = new Director("Sean Baker", 54);
            Movie anora = new Movie("Anora", 2024, seanBaker);
            seanBaker.setMovies(new ArrayList<>((Collections.singletonList(anora))));
            session.persist(seanBaker);
            session.persist(anora);

            // 5) Смените режиссёра у текущего фильма
            Director christopherNolan = session.get(Director.class, 6);
            Movie anora = session.get(Movie.class, 13);
            anora.setDirector(christopherNolan);

            // 6) Удалите фильм у любого режиссёра
            Movie anora = session.get(Movie.class, 13);
            session.remove(anora);

            session.getTransaction().commit();
        }
    }
}
