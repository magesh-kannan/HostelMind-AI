package com.hostelmind.backend.mapper;

import com.hostelmind.backend.dto.HostelDTO;
import com.hostelmind.backend.entity.Hostel;

public class HostelMapper {

    private HostelMapper() {
    }

    public static HostelDTO toDTO(Hostel hostel) {

        if (hostel == null) {
            return null;
        }

        return HostelDTO.builder()
                .id(hostel.getId())
                .hostelName(hostel.getHostelName())
                .address(hostel.getAddress())
                .totalRooms(hostel.getTotalRooms())
                .totalBeds(hostel.getTotalBeds())
                .active(hostel.getActive())
                .build();
    }

    public static Hostel toEntity(HostelDTO dto) {

        if (dto == null) {
            return null;
        }

        Hostel hostel = new Hostel();


        hostel.setHostelName(dto.getHostelName());
        hostel.setAddress(dto.getAddress());
        hostel.setTotalRooms(dto.getTotalRooms());
        hostel.setTotalBeds(dto.getTotalBeds());
        hostel.setActive(dto.getActive());

        return hostel;
    }
}