package com.hostelmind.backend.mapper;

import com.hostelmind.backend.dto.RoomDTO;
import com.hostelmind.backend.entity.Room;

public class RoomMapper {

    private RoomMapper() {
    }

    public static RoomDTO toDTO(Room room) {

        if (room == null) {
            return null;
        }

        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .capacity(room.getCapacity())
                .occupiedBeds(room.getOccupiedBeds())
                .hostelId(room.getHostel() != null ? room.getHostel().getId() : null)
                .build();
    }

    public static Room toEntity(RoomDTO dto) {

        if (dto == null) {
            return null;
        }

        Room room = new Room();

        room.setRoomNumber(dto.getRoomNumber());
        room.setCapacity(dto.getCapacity());
        room.setOccupiedBeds(dto.getOccupiedBeds());

        return room;
    }
}