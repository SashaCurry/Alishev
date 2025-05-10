package ru.alishev.springcourse.firstsecurityapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alishev.springcourse.firstsecurityapp.models.Person;
import ru.alishev.springcourse.firstsecurityapp.services.PersonService;

@Controller
@RequestMapping("/auth")
public class AuthControlller {
    private final PersonService personService;

    @Autowired
    public AuthControlller(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("person") Person person) {
        personService.addUser(person);
        return "redirect:new-user";
    }
}