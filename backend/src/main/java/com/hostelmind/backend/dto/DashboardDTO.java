package com.hostelmind.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private Long totalRooms;

    private Long occupiedRooms;

    private Long vacantRooms;

    private Long totalStudents;

    private Integer occupiedBeds;

    private Integer availableBeds;

    private Double occupancyPercentage;
}