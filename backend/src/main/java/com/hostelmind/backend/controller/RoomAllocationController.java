package com.hostelmind.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostelmind.backend.dto.AllocationRequestDTO;
import com.hostelmind.backend.dto.AllocationResponseDTO;
import com.hostelmind.backend.service.RoomAllocationService;

@RestController
@RequestMapping("/api/allocations")
@CrossOrigin(origins = "*")
public class RoomAllocationController {

    private final RoomAllocationService allocationService;

    public RoomAllocationController(RoomAllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping
    public AllocationResponseDTO allocateRoom(
            @RequestBody AllocationRequestDTO request) {

        return allocationService.allocateRoom(request);
    }

    @GetMapping
    public List<AllocationResponseDTO> getAllAllocations() {

        return allocationService.getAllAllocations();
    }

    @GetMapping("/{id}")
    public AllocationResponseDTO getAllocationById(
            @PathVariable Long id) {

        return allocationService.getAllocationById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<AllocationResponseDTO> getStudentAllocations(
            @PathVariable Long studentId) {

        return allocationService.getStudentAllocations(studentId);
    }

    @GetMapping("/room/{roomId}")
    public List<AllocationResponseDTO> getRoomAllocations(
            @PathVariable Long roomId) {

        return allocationService.getRoomAllocations(roomId);
    }

    @PutMapping("/{id}/vacate")
    public AllocationResponseDTO vacateRoom(
            @PathVariable Long id) {

        return allocationService.vacateRoom(id);
    }
}