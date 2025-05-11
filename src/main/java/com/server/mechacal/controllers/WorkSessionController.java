package com.server.mechacal.controllers;

// import com.server.mechacal.auth.entities.User;
import com.server.mechacal.dto.WorkSessionDto;
// import com.server.mechacal.entities.WorkSession;
// import com.server.mechacal.services.UserInfoService;
import com.server.mechacal.services.WorkSessionService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions")
@AllArgsConstructor
public class WorkSessionController {

    private final WorkSessionService workSessionService;

    @GetMapping("/getAllSessions")
    public ResponseEntity<List<WorkSessionDto>> getAllSessions() 
    {
        List<WorkSessionDto> sessions = workSessionService.getSessions();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/getSession/{sessionId}")
    public ResponseEntity<WorkSessionDto> getSessionById(@PathVariable String sessionId) 
    {
        WorkSessionDto session = workSessionService.getSessionById(sessionId);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/getUserSessions/{userId}")
    public ResponseEntity<List<WorkSessionDto>> getSessionsForUser(@PathVariable String userId) 
    {
        List<WorkSessionDto> sessions = workSessionService.getSessionsForUser(userId);
        return ResponseEntity.ok(sessions);
    }

    @PostMapping("/create")
    public ResponseEntity<WorkSessionDto> createSession(@RequestParam String creatorId, @RequestBody WorkSessionDto dto) 
    {
        WorkSessionDto createdSession = workSessionService.createSession(dto, creatorId);
        return ResponseEntity.ok(createdSession);
    }


    @PutMapping("/update/{sessionId}")
    public ResponseEntity<WorkSessionDto> updateSession(
        @PathVariable String sessionId,
        // @RequestParam String requesterId,
        @RequestBody WorkSessionDto dto) 
    {
        dto.setId(sessionId);
        WorkSessionDto updatedSession = workSessionService.updateSession(dto);
        return ResponseEntity.ok(updatedSession);
    }

    @PostMapping("/addUser/{sessionId}")
    public ResponseEntity<Void> addUserToSession(
        @PathVariable String sessionId,
        @RequestBody String rawEmail) 
    {
        workSessionService.addUser(sessionId, rawEmail);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{sessionId}")
    public ResponseEntity<Void> deleteSession(
        @PathVariable String sessionId,
        @RequestParam String requesterId) 
    {
        workSessionService.deleteSession(sessionId, requesterId);
        return ResponseEntity.noContent().build();
    }

}
