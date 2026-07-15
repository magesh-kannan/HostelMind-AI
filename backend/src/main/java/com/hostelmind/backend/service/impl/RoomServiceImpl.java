package com.hostelmind.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.RoomDTO;
import com.hostelmind.backend.entity.Hostel;
import com.hostelmind.backend.entity.Room;
import com.hostelmind.backend.exception.ResourceNotFoundException;
import com.hostelmind.backend.mapper.RoomMapper;
import com.hostelmind.backend.repository.HostelRepository;
import com.hostelmind.backend.repository.RoomRepository;
import com.hostelmind.backend.repository.StudentRepository;
import com.hostelmind.backend.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HostelRepository hostelRepository;
    private final StudentRepository studentRepository;

    public RoomServiceImpl(RoomRepository roomRepository,
                           HostelRepository hostelRepository,
                           StudentRepository studentRepository) {

        this.roomRepository = roomRepository;
        this.hostelRepository = hostelRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public RoomDTO saveRoom(RoomDTO roomDTO) {

        Hostel hostel = hostelRepository.findById(roomDTO.getHostelId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hostel not found"));

        Room room = RoomMapper.toEntity(roomDTO);
        room.setHostel(hostel);

        Room savedRoom = roomRepository.save(room);

        return RoomMapper.toDTO(savedRoom);
    }

    @Override
    public List<RoomDTO> getAllRooms() {

        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toDTO)
                .toList();
    }

    @Override
    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found"));

        Hostel hostel = hostelRepository.findById(roomDTO.getHostelId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hostel not found"));

        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setCapacity(roomDTO.getCapacity());
        room.setOccupiedBeds(roomDTO.getOccupiedBeds());
        room.setHostel(hostel);

        Room updatedRoom = roomRepository.save(room);

        return RoomMapper.toDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found"));

        if (studentRepository.existsByRoomId(id)) {
            throw new IllegalStateException(
                    "Cannot delete room because students are assigned to it.");
        }

        roomRepository.delete(room);
    }
}