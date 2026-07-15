package com.hostelmind.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private Long id;

    @NotBlank(message = "Register number is required")
    private String registerNumber;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Year of study is required")
    @Min(value = 1, message = "Year of study must be at least 1")
    private Integer yearOfStudy;

    @NotNull(message = "Hostel ID is required")
    private Long hostelId;


    private Long roomId;

    @NotNull(message = "Active status is required")
    private Boolean active;
}