package com.hostelmind.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.entity.Hostel;
import com.hostelmind.backend.repository.HostelRepository;

@Service
public class HostelService {

    private final HostelRepository hostelRepository;

    public HostelService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public Hostel saveHostel(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    public List<Hostel> getAllHostels() {
        return hostelRepository.findAll();
    }
}