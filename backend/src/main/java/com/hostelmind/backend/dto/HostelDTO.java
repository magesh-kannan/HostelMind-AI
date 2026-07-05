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
public class HostelDTO {

    private Long id;

    private String hostelName;

    private String address;

    private Integer totalRooms;

    private Integer totalBeds;

    private Boolean active;
}