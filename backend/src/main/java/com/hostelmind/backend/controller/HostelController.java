package com.hostelmind.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostelmind.backend.entity.Hostel;
import com.hostelmind.backend.service.HostelService;

@RestController
@RequestMapping("/api/hostels")
public class HostelController {

    private final HostelService hostelService;

    public HostelController(HostelService hostelService) {
        this.hostelService = hostelService;
    }

    @PostMapping
    public Hostel createHostel(@RequestBody Hostel hostel) {
        return hostelService.saveHostel(hostel);
    }

    @GetMapping
    public List<Hostel> getAllHostels() {
        return hostelService.getAllHostels();
    }
}