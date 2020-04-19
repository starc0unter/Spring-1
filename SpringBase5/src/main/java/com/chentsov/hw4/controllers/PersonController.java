package com.chentsov.hw4.controllers;

import com.chentsov.hw4.services.PersonService;
import com.chentsov.hw4.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private static final int MIN_PERSONS_IN_PAGE = 10;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String allPersons(@RequestParam(value = "minAge") @Nullable Integer minAge,
                             @RequestParam(value = "maxAge") @Nullable Integer maxAge,
                             @RequestParam(value = "page") @Nullable Integer page,
                             @RequestParam(value = "size") @Nullable Integer size,
                             Model model) {
        model.addAttribute("persons", personService.findAllByAgeBetween(
                minAge, maxAge,
                PageRequest.of(
                        page == null ? 0 : page - 1,
                        size == null ? MIN_PERSONS_IN_PAGE : size
                )
        ));
        model.addAttribute("minAge", minAge);
        model.addAttribute("maxAge", maxAge);
        return "persons";
    }

    @GetMapping("/form")
    public String formPerson(@RequestParam(value = "id") @Nullable Long personId,
                             Model model) {
        if (personId != null) {
            model.addAttribute("person", personService.findById(personId));
        } else {
            model.addAttribute("person", new Person());
        }
        return "person_add";
    }

    @PostMapping("/form")
    public String newPerson(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "person_add";
        }

        personService.save(person);
        return "redirect:/person";
    }
}
