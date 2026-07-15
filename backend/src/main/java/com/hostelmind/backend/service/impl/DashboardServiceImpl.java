package com.hostelmind.backend.service.impl;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.DashboardDTO;
import com.hostelmind.backend.repository.RoomRepository;
import com.hostelmind.backend.repository.StudentRepository;
import com.hostelmind.backend.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final RoomRepository roomRepository;

private final StudentRepository studentRepository;

    @Override
public DashboardDTO getDashboardData() {

    long totalRooms = roomRepository.count();
    long totalStudents = studentRepository.count();

    int occupiedBeds = roomRepository.findAll()
            .stream()
            .mapToInt(r -> r.getOccupiedBeds())
            .sum();
            int totalBeds = roomRepository.findAll()
        .stream()
        .mapToInt(r -> r.getCapacity())
        .sum();

int availableBeds = totalBeds - occupiedBeds;
long occupiedRooms = roomRepository.findAll()
        .stream()
        .filter(room -> room.getOccupiedBeds() > 0)
        .count();

long vacantRooms = totalRooms - occupiedRooms;
double occupancyPercentage = 0.0;

if (totalBeds > 0) {
    occupancyPercentage = ((double) occupiedBeds / totalBeds) * 100;
}

    return DashboardDTO.builder()
        .totalRooms(totalRooms)
        .occupiedRooms(occupiedRooms)
        .vacantRooms(vacantRooms)
        .totalStudents(totalStudents)
        .occupiedBeds(occupiedBeds)
        .availableBeds(availableBeds)
        .occupancyPercentage(occupancyPercentage)
        .build();
}
}