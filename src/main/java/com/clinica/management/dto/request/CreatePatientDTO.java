package com.clinica.management.dto.request;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePatientDTO {
    private String dni;
    private String name;
    private String email;
    private Integer age;
}
