package ru.alishev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByNameAndIdIsNot(String name, Integer id);

    List<Book> findByNameStartingWithIgnoreCase(String prefix);
}
