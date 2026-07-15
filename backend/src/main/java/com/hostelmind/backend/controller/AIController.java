package com.hostelmind.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostelmind.backend.dto.AIRequestDTO;
import com.hostelmind.backend.dto.AIResponseDTO;
import com.hostelmind.backend.service.AIService;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/recommend-room")
    public AIResponseDTO recommendRoom(@RequestBody AIRequestDTO request) {

        return aiService.recommendRoom(request);

    }
}