package com.hostelmind.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.RoomDTO;
import com.hostelmind.backend.entity.Room;
import com.hostelmind.backend.mapper.RoomMapper;
import com.hostelmind.backend.repository.RoomRepository;
import com.hostelmind.backend.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDTO saveRoom(RoomDTO roomDTO) {

        Room room = RoomMapper.toEntity(roomDTO);

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
}