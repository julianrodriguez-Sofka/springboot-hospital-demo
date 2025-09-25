package com.clinica.management.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePatientDTO {
    @NotBlank(message = "DNI can't be blank") //DNI max 10, acepte números
    @Pattern(regexp = "^[0-9]{10}$", message = "DNI error")
    //@Size(max=10, message = "DNI must be exactly 10 characters long")
    private String dni;

    @NotBlank(message = "Name can't be blank")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{2,20}$", message = "Name must only contain letters and spaces")
    private String name;

    @Email
    private String email;

    @NotNull(message = "Age can't be null")
    @Positive(message = "Age can't be negative")
    private Integer age;
}
