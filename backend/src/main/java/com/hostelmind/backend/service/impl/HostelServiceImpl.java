package com.hostelmind.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.HostelDTO;
import com.hostelmind.backend.entity.Hostel;
import com.hostelmind.backend.exception.ResourceNotFoundException;
import com.hostelmind.backend.mapper.HostelMapper;
import com.hostelmind.backend.repository.HostelRepository;
import com.hostelmind.backend.repository.RoomRepository;
import com.hostelmind.backend.service.HostelService;

@Service
public class HostelServiceImpl implements HostelService {

    private final HostelRepository hostelRepository;
    private final RoomRepository roomRepository;

    public HostelServiceImpl(
            HostelRepository hostelRepository,
            RoomRepository roomRepository) {

        this.hostelRepository = hostelRepository;
        this.roomRepository = roomRepository;
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

    @Override
    public void deleteHostel(Long id) {

        Hostel hostel = hostelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hostel not found"));

        if (roomRepository.existsByHostelId(id)) {

            throw new IllegalStateException(
                    "Cannot delete hostel because it contains rooms. Delete all rooms first.");
        }

        hostelRepository.delete(hostel);
    }

    @Override
    public HostelDTO updateHostel(Long id, HostelDTO hostelDTO) {

        Hostel hostel = hostelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hostel not found"));

        hostel.setHostelName(hostelDTO.getHostelName());
        hostel.setAddress(hostelDTO.getAddress());
        hostel.setTotalRooms(hostelDTO.getTotalRooms());
        hostel.setTotalBeds(hostelDTO.getTotalBeds());
        hostel.setActive(hostelDTO.getActive());

        Hostel updatedHostel = hostelRepository.save(hostel);

        return HostelMapper.toDTO(updatedHostel);
    }
}