package com.hostelmind.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.entity.Student;
import com.hostelmind.backend.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

}