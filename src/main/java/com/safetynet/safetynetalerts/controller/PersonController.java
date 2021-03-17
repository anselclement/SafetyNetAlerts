package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class PersonController {

    /*private static Logger logger;

    PersonController(Logger logger){
        this.logger =logger;
    }*/

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public Iterable<Person> getPersons(Model model) {

        Iterable<Person> listPersons = personService.getPersons();

        model.addAttribute("persons",listPersons);

        return listPersons;
    }

    @GetMapping("/createPerson")
    public String createPerson(Model model){
        Person person = new Person();
        model.addAttribute("person", person);
        return "/form/formNewPerson";
    }

    @PostMapping("/savePerson")
    public ModelAndView savePerson(@ModelAttribute Person person){
        personService.savePerson(person);
        return new ModelAndView("redirect:/person");
    }

    @GetMapping("/updatePerson/{id}")
    public String updatePerson(@PathVariable("id") final Long id, Model model){
        Optional<Person> person = personService.getPerson(id);
        model.addAttribute("person", person);
        return "/form/formUpdatePerson";
    }

    @GetMapping("/deletePerson/{lastName}/{firstName}")
    public ModelAndView deleteByLastnameAndFirstname(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName){
        /*logger.info("Récupération de la personne à supprimer" + lastName + " " + firstName);*/
        personService.deleteByLastNameAndFirstName(lastName, firstName);
        return new ModelAndView("redirect:/person");
    }

}
