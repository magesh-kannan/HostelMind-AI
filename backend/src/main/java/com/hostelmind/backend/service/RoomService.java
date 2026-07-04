package com.hostelmind.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.entity.Room;
import com.hostelmind.backend.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}