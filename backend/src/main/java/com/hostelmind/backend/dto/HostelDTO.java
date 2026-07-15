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
public class HostelDTO {

    private Long id;

    @NotBlank(message = "Hostel name is required")
    private String hostelName;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Total rooms is required")
    @Min(value = 1, message = "Total rooms must be at least 1")
    private Integer totalRooms;

    @NotNull(message = "Total beds is required")
    @Min(value = 1, message = "Total beds must be at least 1")
    private Integer totalBeds;

    @NotNull(message = "Active status is required")
    private Boolean active;
}