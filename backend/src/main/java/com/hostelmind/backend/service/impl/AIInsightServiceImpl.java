package com.hostelmind.backend.service.impl;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.AIInsightDTO;
import com.hostelmind.backend.dto.DashboardDTO;
import com.hostelmind.backend.service.AIInsightService;
import com.hostelmind.backend.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIInsightServiceImpl implements AIInsightService {
    private final DashboardService dashboardService;

    @Override
public AIInsightDTO getDashboardInsight() {

    DashboardDTO dashboard = dashboardService.getDashboardData();

    double occupancy = dashboard.getOccupancyPercentage();

    String status;
    String message;
    String recommendation;

    if (occupancy < 40) {

        status = "LOW OCCUPANCY";

        message = "Hostel occupancy is low. There are plenty of beds available.";

        recommendation = "New student admissions can be accommodated without expanding capacity.";

    } else if (occupancy < 80) {

        status = "MEDIUM OCCUPANCY";

        message = "Hostel occupancy is moderate.";

        recommendation = "Monitor room availability and prepare for future admissions.";

    } else {

        status = "HIGH OCCUPANCY";

        message = "Hostel occupancy is high.";

        recommendation = "Consider opening additional rooms or expanding hostel capacity.";

    }

    return AIInsightDTO.builder()
            .status(status)
            .message(message)
            .recommendation(recommendation)
            .build();
}
}