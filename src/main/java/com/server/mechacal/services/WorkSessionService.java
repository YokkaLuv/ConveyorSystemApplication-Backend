package com.server.mechacal.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.mechacal.auth.entities.User;
import com.server.mechacal.auth.repositories.UserRepository;
import com.server.mechacal.dto.WorkSessionDto;
import com.server.mechacal.entities.DesignInput;
import com.server.mechacal.entities.WorkSession;
import com.server.mechacal.entities.Chapter.*;
import com.server.mechacal.repositories.DesignInputRepository;
import com.server.mechacal.repositories.WorkSessionRepository;
import com.server.mechacal.exceptions.ForbiddenException;
import com.server.mechacal.exceptions.UserNotFoundException;

import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkSessionService {

    private final WorkSessionRepository workSessionRepository;
    private final UserRepository userRepository;
    private final DesignInputRepository designInputRepository;

    public WorkSessionService(WorkSessionRepository workSessionRepository, UserRepository userRepository, DesignInputRepository designInputRepository) 
    {
        this.workSessionRepository = workSessionRepository;
        this.userRepository = userRepository;
        this.designInputRepository = designInputRepository;
    }

    public List<WorkSessionDto> getSessions()
    {
        List<WorkSession> sessions = workSessionRepository.findAll();
        List<WorkSessionDto> sessionDtos = new ArrayList<>();
    
        for (WorkSession session : sessions) 
        {
            WorkSessionDto dto = new WorkSessionDto();
            dto.update(session);
            dto.setDesignInputId(session.getDesignInput().getId());
            sessionDtos.add(dto);
        }
    
        return sessionDtos;
    }

    public WorkSessionDto getSessionById(String sessionId) 
    {
        WorkSession session = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("Session not found"));

        WorkSessionDto dto = new WorkSessionDto();
        dto.update(session);
        dto.setDesignInputId(session.getDesignInput().getId());
        return dto;
    }

    public List<WorkSessionDto> getSessionsForUser(String userId) 
    {
        List<WorkSession> sessions = workSessionRepository.findAll();

        List<WorkSessionDto> userSessions = sessions.stream()
            .filter(session -> session.getCreator().getUserId().equals(userId) || 
                               session.getParticipants().stream().anyMatch(user -> user.getUserId().equals(userId)))
            .map(session -> {
                WorkSessionDto dto = new WorkSessionDto();
                dto.update(session);
                dto.setDesignInputId(session.getDesignInput().getId());
                return dto;
            })
            .collect(Collectors.toList());

        return userSessions;
    }

    public WorkSessionDto createSession(WorkSessionDto dto, String creatorId) 
    {
        User creator = userRepository.findById(creatorId)
            .orElseThrow(() -> new UserNotFoundException("Creator not found"));
    
        List<User> participants = new ArrayList<>();
        participants.add(creator);
        if (dto.getParticipantIds() != null) {
            for (String id : dto.getParticipantIds()) {
                User user = userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
                participants.add(user);
            }
        }

        DesignInput designInput = new DesignInput();
        designInput.setChap1(new Chapter1());
        designInput.setChap2(new Chapter2());
        designInput.setChap3(new Chapter3());
        designInput.setChap4(new Chapter4());
        designInput.setChap5(new Chapter5());
        designInput.setChap6(new Chapter6());
        designInput.setChap7(new Chapter7());

        DesignInput savedDesignInput = designInputRepository.save(designInput);
    
        WorkSession session = WorkSession.builder()
                .title(dto.getTitle())
                .creator(creator)
                .createdAt(LocalDateTime.now())
                .status(dto.getStatus() != null ? dto.getStatus() : "IN_PROGRESS")
                .participants(participants)
                .designInput(savedDesignInput)
                .build();
    
        WorkSession savedSession = workSessionRepository.save(session);

        String sessionId = savedSession.getId();

        for (User participant : participants) {
            if (participant.getSessionIds() == null) {
                participant.setSessionIds(new ArrayList<>());
            }
            participant.getSessionIds().add(sessionId);
        }

        userRepository.saveAll(participants);
    
        WorkSessionDto response = new WorkSessionDto();
        response.update(savedSession);
        response.setDesignInputId(savedDesignInput.getId());
        return response;
    }

    public WorkSessionDto updateSession(WorkSessionDto dto) 
    {
        WorkSession session = workSessionRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Session not found"));

        // if (!session.getCreator().getUserId().equals(dto.getCreatorId()) && session.getParticipants().stream().noneMatch(user -> user.getUserId().equals(dto.getCreatorId()))) 
        // {
            // throw new ForbiddenException("Only the creator or participants can update this session");
        // }

        if (dto.getTitle() != null) session.setTitle(dto.getTitle());
        if (dto.getStatus() != null) session.setStatus(dto.getStatus());
    
        WorkSession updated = workSessionRepository.save(session);
    
        WorkSessionDto response = new WorkSessionDto();
        response.update(updated);
        response.setDesignInputId(updated.getDesignInput().getId());
        return response;
    }
    

    public void deleteSession(String sessionId, String requesterId) 
    {
        WorkSession session = workSessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));
        
        if (!session.getCreator().getUserId().equals(requesterId)) 
        {
            throw new ForbiddenException("Only the creator can delete this session");
        }
        designInputRepository.deleteById(session.getDesignInput().getId());
        workSessionRepository.deleteById(sessionId);
    }

    private String extractEmail(String rawEmail) {
        try {
            // Use Jackson ObjectMapper for minimal JSON parsing
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(rawEmail);
            return root.get("email").asText();
        } catch (Exception e) {
            throw new RuntimeException("Invalid email format in request body: " + rawEmail, e);
        }
    }
    
    public WorkSessionDto addUser(String sessionId, String rawEmail) 
    {
        WorkSession session = workSessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));

        String email = extractEmail(rawEmail);
    
        // boolean isAuthorized = session.getCreator().getUserId().equals(requesterId) ||
        //     session.getParticipants().stream().anyMatch(u -> u.getUserId().equals(requesterId));
    
        // if (!isAuthorized) 
        // {
        //     throw new ForbiddenException("You do not have permission to add users to this session");
        // }
    
        User userToAdd = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found: " + email));
    
        boolean alreadyAdded = session.getParticipants().stream().anyMatch(u -> u.getUserId().equals(userToAdd.getUserId()));
    
        if (alreadyAdded) 
        {
            throw new RuntimeException("User already added to this session");
        }
        session.getParticipants().add(userToAdd);
        WorkSession saved = workSessionRepository.save(session);

        WorkSessionDto dto = new WorkSessionDto();
        dto.update(saved);
        dto.setDesignInputId(saved.getDesignInput().getId());
        return dto;
    }
    
}