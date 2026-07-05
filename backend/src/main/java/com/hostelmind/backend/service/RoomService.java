package com.hostelmind.backend.service;

import java.util.List;

import com.hostelmind.backend.dto.RoomDTO;

public interface RoomService {

    RoomDTO saveRoom(RoomDTO roomDTO);

    List<RoomDTO> getAllRooms();
}