package ru.job4j.auth.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "persons")
public class Person {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id can't be null")
    @PositiveOrZero(message = "Id can't be minus.")
    private int id;

    @NotBlank(message = "Login can't be empty.")
    private String login;

    @NotBlank(message = "Password can't be empty.")
    @Size(min = 2, message = "Password should has size min 2 characters.")
    private String password;
}
