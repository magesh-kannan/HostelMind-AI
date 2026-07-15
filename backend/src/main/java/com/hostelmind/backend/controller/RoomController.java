package com.hostelmind.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostelmind.backend.dto.RoomDTO;
import com.hostelmind.backend.service.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public RoomDTO createRoom(@Valid @RequestBody RoomDTO roomDTO) {

        return roomService.saveRoom(roomDTO);

    }

    @PutMapping("/{id}")
    public RoomDTO updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomDTO roomDTO) {

        return roomService.updateRoom(id, roomDTO);

    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {

        roomService.deleteRoom(id);

        return "Room deleted successfully.";

    }

    @GetMapping
    public List<RoomDTO> getAllRooms() {

        return roomService.getAllRooms();

    }
}