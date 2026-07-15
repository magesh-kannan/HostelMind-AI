package com.hostelmind.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.StudentDTO;
import com.hostelmind.backend.entity.Hostel;
import com.hostelmind.backend.entity.Room;
import com.hostelmind.backend.entity.Student;
import com.hostelmind.backend.exception.DuplicateResourceException;
import com.hostelmind.backend.exception.ResourceNotFoundException;
import com.hostelmind.backend.mapper.StudentMapper;
import com.hostelmind.backend.repository.HostelRepository;
import com.hostelmind.backend.repository.RoomRepository;
import com.hostelmind.backend.repository.StudentRepository;
import com.hostelmind.backend.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final HostelRepository hostelRepository;
    private final RoomRepository roomRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              HostelRepository hostelRepository,
                              RoomRepository roomRepository) {

        this.studentRepository = studentRepository;
        this.hostelRepository = hostelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {

        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (studentRepository.findByRegisterNumber(studentDTO.getRegisterNumber()).isPresent()) {
            throw new DuplicateResourceException("Register number already exists.");
        }

        Student student = StudentMapper.toEntity(studentDTO);

        Hostel hostel = hostelRepository.findById(studentDTO.getHostelId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hostel not found with ID: " + studentDTO.getHostelId()));

        Room room = roomRepository.findById(studentDTO.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room not found with ID: " + studentDTO.getRoomId()));

        student.setHostel(hostel);
        student.setRoom(room);

        Student savedStudent = studentRepository.save(student);

        return StudentMapper.toDTO(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .toList();
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with ID: " + id));

        studentRepository.findByEmail(studentDTO.getEmail())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new DuplicateResourceException("Email already exists.");
                    }
                });

        studentRepository.findByRegisterNumber(studentDTO.getRegisterNumber())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new DuplicateResourceException("Register number already exists.");
                    }
                });

        Hostel hostel = hostelRepository.findById(studentDTO.getHostelId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hostel not found with ID: " + studentDTO.getHostelId()));

        Room room = roomRepository.findById(studentDTO.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room not found with ID: " + studentDTO.getRoomId()));

        student.setRegisterNumber(studentDTO.getRegisterNumber());
        student.setFullName(studentDTO.getFullName());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());
        student.setDepartment(studentDTO.getDepartment());
        student.setYearOfStudy(studentDTO.getYearOfStudy());
        student.setActive(studentDTO.getActive());
        student.setHostel(hostel);
        student.setRoom(room);

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.toDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with ID: " + id));

        studentRepository.delete(student);
    }

}