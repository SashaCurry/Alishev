package ru.alishev.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.services.BooksService;
import ru.alishev.springcourse.services.PeopleService;
import ru.alishev.springcourse.util.BookValidator;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BooksService booksService;
    private final BookValidator bookValidator;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, BookValidator bookValidator, PeopleService peopleService) {
        this.booksService = booksService;
        this.bookValidator = bookValidator;
        this.peopleService = peopleService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
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

        booksService.save(book);
        return "redirect:/book";
    }


    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOne(id));
        return "book/edit";
    }


    @PatchMapping()
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @ModelAttribute("person") Person person, @RequestParam("personId") int personId) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "book/edit";

        book.setOwner(peopleService.findOne(personId));
        booksService.save(book);
        return "redirect:/book";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = booksService.findOne(id);
        model.addAttribute("book", book);

        if (book.getOwner() == null) {
            model.addAttribute("people", peopleService.findAll());
            model.addAttribute("newPerson", new Person());
        }
        else
            model.addAttribute("person", peopleService.findOne(book.getOwner().getId()));

        System.out.println(book);
        return "book/show";
    }


    @PatchMapping("/{id}")
    public String assignBook(@PathVariable("id") int id,
                             @ModelAttribute("nullField") String nullField, @ModelAttribute("newPerson") Person person) {
        Book book = booksService.findOne(id);

        if ("null".equals(nullField))
            book.setOwner(null);
        booksService.assignBook(book, person);

        return "redirect:/book/" + id;
    }


    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/book";
    }
}
