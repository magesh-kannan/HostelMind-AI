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

import com.hostelmind.backend.dto.HostelDTO;
import com.hostelmind.backend.service.HostelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hostels")
public class HostelController {

    private final HostelService hostelService;

    public HostelController(HostelService hostelService) {
        this.hostelService = hostelService;
    }

    @PostMapping
    public HostelDTO createHostel(@Valid @RequestBody HostelDTO hostelDTO) {

        return hostelService.saveHostel(hostelDTO);

    }

    @PutMapping("/{id}")
    public HostelDTO updateHostel(
            @PathVariable Long id,
            @Valid @RequestBody HostelDTO hostelDTO) {

        return hostelService.updateHostel(id, hostelDTO);

    }

    @DeleteMapping("/{id}")
    public String deleteHostel(@PathVariable Long id) {

        hostelService.deleteHostel(id);

        return "Hostel deleted successfully.";

    }

    @GetMapping
    public List<HostelDTO> getAllHostels() {

        return hostelService.getAllHostels();

    }
}