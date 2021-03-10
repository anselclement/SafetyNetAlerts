package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public Iterable<Person> getPersons(Model model) {

        Iterable<Person> listPersons = personService.getPersons();

        model.addAttribute("persons",listPersons);

        return listPersons;
    }
}
