package ru.alishev.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.util.BookValidator;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final BookValidator bookValidator;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, BookValidator bookValidator, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        System.out.println("Request got");
        model.addAttribute("books", bookDAO.index());
        System.out.println("Model loaded");
        return "book/index";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "book/new";

        bookDAO.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping()
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "book/edit";

        bookDAO.update(book);
        return "redirect:/book";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.show(id);
        model.addAttribute("book", book);

        if (book.getPerson_id() == null)
            model.addAttribute("people", personDAO.index());
        else
            model.addAttribute("person", personDAO.show(book.getPerson_id()));

        return "book/show";
    }

    @PatchMapping("/{id}")
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("book") Book book,
                           @ModelAttribute("nullField") String nullField) {
        if ("null".equals(nullField))
            book.setPerson_id(null);
        bookDAO.assignBook(id, book);

        return "redirect:/book/" + id;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }
}
