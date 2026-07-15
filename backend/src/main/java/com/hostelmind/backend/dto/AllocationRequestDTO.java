package com.hostelmind.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllocationRequestDTO {

    private Long studentId;

    private Long roomId;

    private String remarks;
}