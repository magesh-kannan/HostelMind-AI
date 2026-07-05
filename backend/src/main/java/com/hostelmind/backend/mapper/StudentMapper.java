package com.hostelmind.backend.mapper;

import com.hostelmind.backend.dto.StudentDTO;
import com.hostelmind.backend.entity.Student;

public class StudentMapper {

    private StudentMapper() {
    }

    public static StudentDTO toDTO(Student student) {

        if (student == null) {
            return null;
        }

        return StudentDTO.builder()
                .id(student.getId())
                .registerNumber(student.getRegisterNumber())
                .fullName(student.getFullName())
                .email(student.getEmail())
                .phone(student.getPhone())
                .department(student.getDepartment())
                .yearOfStudy(student.getYearOfStudy())
                .hostelId(student.getHostel() != null ? student.getHostel().getId() : null)
                .roomId(student.getRoom() != null ? student.getRoom().getId() : null)
                .active(student.getActive())
                .build();
    }

    public static Student toEntity(StudentDTO dto) {

        if (dto == null) {
            return null;
        }

        Student student = new Student();

        student.setRegisterNumber(dto.getRegisterNumber());
        student.setFullName(dto.getFullName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setDepartment(dto.getDepartment());
        student.setYearOfStudy(dto.getYearOfStudy());
        student.setActive(dto.getActive());

        return student;
    }
}