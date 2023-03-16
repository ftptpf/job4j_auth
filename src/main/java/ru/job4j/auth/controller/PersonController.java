package ru.job4j.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.auth.dto.PersonDTO;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @GetMapping("/")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        Person person = personService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Person is not found. Please, check id."));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        if (person.getPassword().contains("*")) {
            throw new IllegalArgumentException("Password contains *. It's not allowed symbol.");
        }
        if (person.getLogin().isBlank() || person.getPassword().isBlank()) {
            throw new NullPointerException("Invalid username or password.");
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Person person) {
        Optional<Person> personFromRepository = personService.findById(person.getId());
        if (personFromRepository.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (person.getLogin().isBlank() || person.getPassword().isBlank()) {
            throw new NullPointerException("Invalid username or password.");
        }
        personService.save(person);
        return ResponseEntity.ok("Success update");
    }

    @PatchMapping("/")
    public ResponseEntity<?> passwordUpdate(@RequestBody PersonDTO personDTO) {
        Optional<Person> personFromRepository = personService.findById(personDTO.getId());
        if (personFromRepository.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (personDTO.getPassword().isBlank()) {
            throw new NullPointerException("Invalid password.");
        }
        String login = personFromRepository.get().getLogin();
        Person person = new Person(personDTO.getId(), login, passwordEncoder.encode(personDTO.getPassword()));
        personService.save(person);
        return ResponseEntity.ok(String.format("The %s password has been successfully updated.", login));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Person> personFromRepository = personService.findById(id);
        if (personFromRepository.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Person person = new Person();
        person.setId(id);
        personService.delete(person);
        return ResponseEntity.ok("Success delete");
    }


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void handleException(Exception e,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        Map<String, String> exceptionInformation = new HashMap<>();
        exceptionInformation.put("message", "Illegal arguments in fields");
        exceptionInformation.put("details", e.getMessage());

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(exceptionInformation));
    }

}
