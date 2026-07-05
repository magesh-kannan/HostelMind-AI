package com.hostelmind.backend.dto;

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

    private String registerNumber;

    private String fullName;

    private String email;

    private String phone;

    private String department;

    private Integer yearOfStudy;

    private Long hostelId;

    private Long roomId;

    private Boolean active;
}