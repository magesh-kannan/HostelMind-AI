package com.hostelmind.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostelmind.backend.dto.StudentDTO;
import com.hostelmind.backend.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public StudentDTO create(@Valid @RequestBody StudentDTO studentDTO) {
        return service.saveStudent(studentDTO);
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.getAllStudents();
    }

    @PutMapping("/{id}")
    public StudentDTO update(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO studentDTO) {

        return service.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        service.deleteStudent(id);

        return "Student deleted successfully.";
    }

}