package com.hostelmind.backend.mapper;

import com.hostelmind.backend.dto.AllocationResponseDTO;
import com.hostelmind.backend.entity.RoomAllocation;

public class RoomAllocationMapper {

    public static AllocationResponseDTO toDTO(RoomAllocation allocation) {

        return AllocationResponseDTO.builder()
                .allocationId(allocation.getId())
                .studentName(allocation.getStudent().getFullName())
                .registerNumber(allocation.getStudent().getRegisterNumber())
                .roomNumber(allocation.getRoom().getRoomNumber())
                .hostelName(allocation.getRoom().getHostel().getHostelName())
                .allocatedDate(allocation.getAllocatedDate())
                .status(allocation.getStatus())
                .build();
    }

}