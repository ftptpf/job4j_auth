package ru.job4j.auth.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonDTO {

    @NotNull(message = "Id can't be null")
    @PositiveOrZero(message = "Id can't be minus.")
    private int id;

    @NotBlank(message = "Password can't be empty.")
    @Size(min = 2, message = "Password should has size min 2 characters.")
    private String password;
}
