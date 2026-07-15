package com.hostelmind.backend.dto;

import java.time.LocalDate;

import com.hostelmind.backend.enums.AllocationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllocationResponseDTO {

    private Long allocationId;

    private String studentName;

    private String registerNumber;

    private String roomNumber;

    private String hostelName;

    private LocalDate allocatedDate;

    private AllocationStatus status;
}