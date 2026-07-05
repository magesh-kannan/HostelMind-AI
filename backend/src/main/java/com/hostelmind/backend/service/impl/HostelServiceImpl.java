package com.hostelmind.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.HostelDTO;
import com.hostelmind.backend.entity.Hostel;
import com.hostelmind.backend.mapper.HostelMapper;
import com.hostelmind.backend.repository.HostelRepository;
import com.hostelmind.backend.service.HostelService;

@Service
public class HostelServiceImpl implements HostelService {

    private final HostelRepository hostelRepository;

    public HostelServiceImpl(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    @Override
    public HostelDTO saveHostel(HostelDTO hostelDTO) {

        Hostel hostel = HostelMapper.toEntity(hostelDTO);

        Hostel savedHostel = hostelRepository.save(hostel);

        return HostelMapper.toDTO(savedHostel);
    }

    @Override
    public List<HostelDTO> getAllHostels() {

        return hostelRepository.findAll()
                .stream()
                .map(HostelMapper::toDTO)
                .toList();
    }
}