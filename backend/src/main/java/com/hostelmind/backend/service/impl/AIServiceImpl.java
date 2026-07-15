package com.hostelmind.backend.service.impl;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.AIRequestDTO;
import com.hostelmind.backend.dto.AIResponseDTO;
import com.hostelmind.backend.entity.Room;
import com.hostelmind.backend.repository.RoomRepository;
import com.hostelmind.backend.repository.StudentRepository;
import com.hostelmind.backend.service.AIService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final StudentRepository studentRepository;

    private final RoomRepository roomRepository;

    @Override
public AIResponseDTO recommendRoom(AIRequestDTO request) {

    Room bestRoom = roomRepository.findAll()
            .stream()
            .filter(room -> room.getOccupiedBeds() < room.getCapacity())
            .max(Comparator.comparingInt(room ->
                    room.getCapacity() - room.getOccupiedBeds()))
            .orElse(null);

    if (bestRoom == null) {

        return AIResponseDTO.builder()
                .recommendedRoom("No Room")
                .occupancyStatus("FULL")
                .recommendation("All rooms are fully occupied.")
                .build();
    }

    int availableBeds = bestRoom.getCapacity() - bestRoom.getOccupiedBeds();

    String status = availableBeds >= 3 ? "LOW OCCUPANCY"
            : availableBeds >= 1 ? "MEDIUM OCCUPANCY"
            : "FULL";

    return AIResponseDTO.builder()
            .recommendedRoom(bestRoom.getRoomNumber())
            .occupancyStatus(status)
            .recommendation(
                    "Room " + bestRoom.getRoomNumber()
                            + " is recommended because it has "
                            + availableBeds
                            + " available bed(s).")
            .build();
}
}