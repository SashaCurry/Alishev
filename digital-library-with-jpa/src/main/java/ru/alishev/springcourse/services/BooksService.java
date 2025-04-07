package ru.alishev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BooksRepository;
import ru.alishev.springcourse.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }
    public List<Book> findAll(Integer page, Integer booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent(); // Пагинация
    }
    public List<Book> findAll(Boolean sortByYear) {
        return booksRepository.findAll(Sort.by("yearReleased")); // Сортировка по году
    }
    public List<Book> findAll(Integer page, Integer booksPerPage, Boolean sortByYear) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearReleased"))).getContent(); // Пагинация + Сортировка
    }

    public Book findOne(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    public Optional<Book> isExist(String name, int id) {
        return booksRepository.findByNameAndIdIsNot(name, id);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void assignBook(Book book, Person person, boolean havePerson) {
        if (havePerson) {
            book.setOwner(peopleRepository.findById(person.getId()).orElse(null));
            book.setDateAssign(new Date());
        }
        else {
            book.setOwner(null);
            book.setDateAssign(null);
        }
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public List<Book> findLikeStartWith(String prefix) {
        return booksRepository.findByNameStartingWithIgnoreCase(prefix);
    }
}
