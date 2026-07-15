package com.hostelmind.backend.service;

import com.hostelmind.backend.dto.AIRequestDTO;
import com.hostelmind.backend.dto.AIResponseDTO;

public interface AIService {

    AIResponseDTO recommendRoom(AIRequestDTO request);

}