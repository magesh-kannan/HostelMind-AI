package com.hostelmind.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostelmind.backend.dto.StudentDTO;
import com.hostelmind.backend.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public StudentDTO create(@RequestBody StudentDTO studentDTO) {
        return service.saveStudent(studentDTO);
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.getAllStudents();
    }
}