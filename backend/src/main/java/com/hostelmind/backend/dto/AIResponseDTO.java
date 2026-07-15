package com.hostelmind.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIResponseDTO {

    private String recommendedRoom;

    private String occupancyStatus;

    private String recommendation;

}