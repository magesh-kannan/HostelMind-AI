package com.hostelmind.backend.service;

import java.util.List;

import com.hostelmind.backend.dto.StudentDTO;

public interface StudentService {

    StudentDTO saveStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();
}