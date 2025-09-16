package com.clinica.management.dto.response;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PatientDTO {
    private Long id;
    private String dni;
    private String name;
    private String email;
    private Integer age;
}