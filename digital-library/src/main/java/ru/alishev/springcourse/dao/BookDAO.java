package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index() {
        System.out.println("index is working...");
        return jdbcTemplate.query("SELECT * FROM Book ORDER BY name", new BeanPropertyRowMapper<>(Book.class));
    }


    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
                                  new BeanPropertyRowMapper<>(Book.class))
                           .stream().findAny().orElse(null);
    }
    public Optional<Book> show(int id, String name) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=? AND book_id <> ?", new Object[]{name, id},
                                  new BeanPropertyRowMapper<>(Book.class))
                           .stream().findAny();
    }


    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name, author, year_released) VALUES (?, ?, ?)",
                            book.getName(), book.getAuthor(), book.getYear_released());
    }


    public void update(Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_released=? WHERE book_id=?",
                             book.getName(), book.getAuthor(), book.getYear_released(), book.getBook_id());
    }


    public void assignBook(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", book.getPerson_id(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
}
