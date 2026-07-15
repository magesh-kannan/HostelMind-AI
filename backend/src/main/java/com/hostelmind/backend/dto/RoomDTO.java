package com.hostelmind.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    @NotNull(message = "Occupied beds is required")
    @Min(value = 0, message = "Occupied beds cannot be negative")
    private Integer occupiedBeds;

    @NotNull(message = "Hostel ID is required")
    private Long hostelId;
}