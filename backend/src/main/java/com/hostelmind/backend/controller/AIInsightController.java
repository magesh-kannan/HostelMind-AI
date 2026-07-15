package com.hostelmind.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostelmind.backend.dto.AIInsightDTO;
import com.hostelmind.backend.service.AIInsightService;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIInsightController {

    @Autowired
    private AIInsightService aiInsightService;

    @GetMapping("/dashboard-insight")
    public AIInsightDTO getDashboardInsight() {

        return aiInsightService.getDashboardInsight();

    }
}