package com.hostelmind.backend.service;

import java.util.List;

import com.hostelmind.backend.dto.AllocationRequestDTO;
import com.hostelmind.backend.dto.AllocationResponseDTO;

public interface RoomAllocationService {

    AllocationResponseDTO allocateRoom(AllocationRequestDTO request);

    List<AllocationResponseDTO> getAllAllocations();

    AllocationResponseDTO getAllocationById(Long id);

    List<AllocationResponseDTO> getStudentAllocations(Long studentId);

    List<AllocationResponseDTO> getRoomAllocations(Long roomId);

    AllocationResponseDTO vacateRoom(Long allocationId);

}