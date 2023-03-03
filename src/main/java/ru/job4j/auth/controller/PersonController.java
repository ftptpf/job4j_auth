package ru.job4j.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.auth.repository.PersonRepository;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonRepository repository;
}
