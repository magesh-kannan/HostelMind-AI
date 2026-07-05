package com.hostelmind.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostelmind.backend.dto.HostelDTO;
import com.hostelmind.backend.service.HostelService;

@RestController
@RequestMapping("/api/hostels")
public class HostelController {

    private final HostelService hostelService;

    public HostelController(HostelService hostelService) {
        this.hostelService = hostelService;
    }

    @PostMapping
    public HostelDTO createHostel(@RequestBody HostelDTO hostelDTO) {
        return hostelService.saveHostel(hostelDTO);
    }

    @GetMapping
    public List<HostelDTO> getAllHostels() {
        return hostelService.getAllHostels();
    }
}