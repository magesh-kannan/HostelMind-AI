package com.hostelmind.backend.service;

import java.util.List;

import com.hostelmind.backend.dto.HostelDTO;

public interface HostelService {

    HostelDTO saveHostel(HostelDTO hostelDTO);

    List<HostelDTO> getAllHostels();
}