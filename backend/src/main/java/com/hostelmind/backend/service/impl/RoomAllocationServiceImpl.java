package com.hostelmind.backend.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.AllocationRequestDTO;
import com.hostelmind.backend.dto.AllocationResponseDTO;
import com.hostelmind.backend.entity.Room;
import com.hostelmind.backend.entity.RoomAllocation;
import com.hostelmind.backend.entity.Student;
import com.hostelmind.backend.enums.AllocationStatus;
import com.hostelmind.backend.mapper.RoomAllocationMapper;
import com.hostelmind.backend.repository.RoomAllocationRepository;
import com.hostelmind.backend.repository.RoomRepository;
import com.hostelmind.backend.repository.StudentRepository;
import com.hostelmind.backend.service.RoomAllocationService;

@Service
public class RoomAllocationServiceImpl implements RoomAllocationService {

    private final RoomAllocationRepository allocationRepository;
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;

    public RoomAllocationServiceImpl(
            RoomAllocationRepository allocationRepository,
            StudentRepository studentRepository,
            RoomRepository roomRepository) {

        this.allocationRepository = allocationRepository;
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public AllocationResponseDTO allocateRoom(AllocationRequestDTO request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (allocationRepository.existsByStudentIdAndStatus(
                student.getId(),
                AllocationStatus.ACTIVE)) {

            throw new RuntimeException("Student already has an active room allocation");
        }

        if (room.getOccupiedBeds() >= room.getCapacity()) {

            throw new RuntimeException("Room is already full");
        }

        RoomAllocation allocation = new RoomAllocation();

        allocation.setStudent(student);
        allocation.setRoom(room);
        allocation.setAllocatedDate(LocalDate.now());
        allocation.setStatus(AllocationStatus.ACTIVE);
        allocation.setRemarks(request.getRemarks());

        RoomAllocation savedAllocation = allocationRepository.save(allocation);

        room.setOccupiedBeds(room.getOccupiedBeds() + 1);
        roomRepository.save(room);

        student.setRoom(room);
        studentRepository.save(student);

        return RoomAllocationMapper.toDTO(savedAllocation);
    }

    @Override
    public List<AllocationResponseDTO> getAllAllocations() {

        return allocationRepository.findAll()
                .stream()
                .map(RoomAllocationMapper::toDTO)
                .toList();
    }

    @Override
    public AllocationResponseDTO getAllocationById(Long id) {

        RoomAllocation allocation = allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));

        return RoomAllocationMapper.toDTO(allocation);
    }

    @Override
    public List<AllocationResponseDTO> getStudentAllocations(Long studentId) {

        return allocationRepository.findAll()
                .stream()
                .filter(allocation ->
                        allocation.getStudent().getId().equals(studentId))
                .map(RoomAllocationMapper::toDTO)
                .toList();
    }

    @Override
    public List<AllocationResponseDTO> getRoomAllocations(Long roomId) {

        return allocationRepository.findAll()
                .stream()
                .filter(allocation ->
                        allocation.getRoom().getId().equals(roomId))
                .map(RoomAllocationMapper::toDTO)
                .toList();
    }

    @Override
    public AllocationResponseDTO vacateRoom(Long allocationId) {

        RoomAllocation allocation = allocationRepository.findById(allocationId)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));

        if (allocation.getStatus() != AllocationStatus.ACTIVE) {
            throw new RuntimeException("Room is already vacated");
        }

        allocation.setStatus(AllocationStatus.VACATED);
        allocation.setVacatedDate(LocalDate.now());

        Room room = allocation.getRoom();

        room.setOccupiedBeds(room.getOccupiedBeds() - 1);
        roomRepository.save(room);

        Student student = allocation.getStudent();

        student.setRoom(null);
        studentRepository.save(student);

        RoomAllocation updatedAllocation = allocationRepository.save(allocation);

        return RoomAllocationMapper.toDTO(updatedAllocation);
    }
}