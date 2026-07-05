package com.hostelmind.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.StudentDTO;
import com.hostelmind.backend.entity.Student;
import com.hostelmind.backend.mapper.StudentMapper;
import com.hostelmind.backend.repository.StudentRepository;
import com.hostelmind.backend.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {

        Student student = StudentMapper.toEntity(studentDTO);

        Student savedStudent = repository.save(student);

        return StudentMapper.toDTO(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        return repository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .toList();
    }
}