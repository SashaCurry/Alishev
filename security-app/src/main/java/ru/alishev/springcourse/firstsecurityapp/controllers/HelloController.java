package ru.alishev.springcourse.firstsecurityapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.alishev.springcourse.firstsecurityapp.models.Person;
import ru.alishev.springcourse.firstsecurityapp.security.PersonDetails;
import ru.alishev.springcourse.firstsecurityapp.services.PersonService;

@Controller
public class HelloController {
    private final PersonService personService;

    @Autowired
    public HelloController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }


    @GetMapping("/forUser")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String forUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return "forUser";
    }


    @GetMapping("/forAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String forAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return "forAdmin";
    }


    @PostMapping("/new-user")
    public String addUser(@RequestBody Person person) {
        personService.addUser(person);
        return "new-user";
    }
}
