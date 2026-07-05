package com.hostelmind.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {

    private Long id;

    private String roomNumber;

    private Integer capacity;

    private Integer occupiedBeds;

    private Long hostelId;
}