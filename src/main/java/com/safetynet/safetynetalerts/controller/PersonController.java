package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
public class PersonController {

    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonDAO personDAO;

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
        logger.info("Mise à jour de la person");
        Optional<Person> person = personService.getPerson(id);
        model.addAttribute("person", person);
        return "/form/formUpdatePerson";
    }

    @GetMapping("/deletePerson/{lastName}/{firstName}")
    public ModelAndView deleteByLastnameAndFirstname(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName){
        personService.deleteByLastNameAndFirstName(lastName, firstName);
        logger.info("Récupération de la personne à supprimer" + lastName + " " + firstName);
        return new ModelAndView("redirect:/person");
    }

    @GetMapping("/communityEmail{city}")
    public String listPersonsEmailByCity(@RequestParam(value = "city") String city, Model model){
        List listPersonsEmailByCity = personDAO.getPersonsEmailByCity(city);
        model.addAttribute("listPersonsEmailByCity", listPersonsEmailByCity);
        return "/personsEmailByCity";
    }
}
