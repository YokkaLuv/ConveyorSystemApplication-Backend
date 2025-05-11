package com.server.mechacal.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.mechacal.dto.DesignInputDto;
import com.server.mechacal.dto.Chapter.*;
import com.server.mechacal.services.DesignInputService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/design-input")
@AllArgsConstructor
public class DesignInputController {
    private final DesignInputService designInputService;

    @GetMapping("/{sessionId}")
    public ResponseEntity<DesignInputDto> getDesignInputBySessionId(@PathVariable String sessionId) {
        DesignInputDto designInputDto = designInputService.getDesignInputBySessionId(sessionId);
        return ResponseEntity.ok(designInputDto);
    }

    @GetMapping("/{sessionId}/chapter1")
    public ResponseEntity<Chapter1Dto> getChapter1(@PathVariable String sessionId) {
        Chapter1Dto dto = designInputService.getChapter1(sessionId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{sessionId}/chapter1/update")
    public ResponseEntity<Chapter1Dto> updateChapter1(@PathVariable String sessionId, @RequestBody Chapter1Dto dto) {
        Chapter1Dto updatedDto = designInputService.updateChapter1(sessionId, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @PostMapping("/{sessionId}/chapter1/calculate")
    public ResponseEntity<Chapter1Dto> calculateChapter1Output(@PathVariable String sessionId) {
        Chapter1Dto calculatedDto = designInputService.calculateChapter1Output(sessionId);
        return ResponseEntity.ok(calculatedDto);
    }

    @PostMapping("/{sessionId}/chapter1/save")
    public ResponseEntity<Chapter1Dto> saveChapter1Output(@PathVariable String sessionId) {
        Chapter1Dto savedDto = designInputService.saveChapter1Output(sessionId);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{sessionId}/chapter2")
    public ResponseEntity<Chapter2Dto> getChapter2(@PathVariable String sessionId) {
        Chapter2Dto dto = designInputService.getChapter2(sessionId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{sessionId}/chapter2/calculate")
    public ResponseEntity<Chapter2Dto> calculateChapter2Output(@PathVariable String sessionId) {
        Chapter2Dto calculatedDto = designInputService.calculateChapter2Output(sessionId);
        return ResponseEntity.ok(calculatedDto);
    }

    @PostMapping("/{sessionId}/chapter2/save")
    public ResponseEntity<Chapter2Dto> saveChapter2Output(@PathVariable String sessionId) {
        Chapter2Dto savedDto = designInputService.saveChapter2Output(sessionId);
        return ResponseEntity.ok(savedDto);
    }

    @PutMapping("/{sessionId}/chapter2/update")
    public ResponseEntity<Chapter2Dto> updateChapter2(@PathVariable String sessionId, 
                    @RequestBody Chapter2Dto dto, 
                    @RequestParam(required = false, defaultValue = "false") boolean imported) {
        Chapter2Dto updatedDto = designInputService.updateChapter2(sessionId, dto, imported);
        return ResponseEntity.ok(updatedDto);
    }

    @GetMapping("/{sessionId}/chapter3")
    public ResponseEntity<Chapter3Dto> getChapter3(@PathVariable String sessionId) {
        Chapter3Dto dto = designInputService.getChapter3(sessionId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{sessionId}/chapter3/calculate")
    public ResponseEntity<Chapter3Dto> calculateChapter3Output(@PathVariable String sessionId) {
        Chapter3Dto calculatedDto = designInputService.calculateChapter3Output(sessionId);
        return ResponseEntity.ok(calculatedDto);
    }

    @PostMapping("/{sessionId}/chapter3/save")
    public ResponseEntity<Chapter3Dto> saveChapter3Output(@PathVariable String sessionId) {
        Chapter3Dto savedDto = designInputService.saveChapter3Output(sessionId);
        return ResponseEntity.ok(savedDto);
    }

    @PutMapping("/{sessionId}/chapter3/update")
    public ResponseEntity<Chapter3Dto> updateChapter3(@PathVariable String sessionId, 
                    @RequestBody Chapter3Dto dto, 
                    @RequestParam(required = false, defaultValue = "false") boolean imported) {
        Chapter3Dto updatedDto = designInputService.updateChapter3(sessionId, dto, imported);
        return ResponseEntity.ok(updatedDto);
    }

    @GetMapping("/{sessionId}/chapter4")
    public ResponseEntity<Chapter4Dto> getChapter4(@PathVariable String sessionId) {
        Chapter4Dto dto = designInputService.getChapter4(sessionId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{sessionId}/chapter4/calculate")
    public ResponseEntity<Chapter4Dto> calculateChapter4Output(@PathVariable String sessionId) {
        Chapter4Dto calculatedDto = designInputService.calculateChapter4Output(sessionId);
        return ResponseEntity.ok(calculatedDto);
    }

    @PostMapping("/{sessionId}/chapter4/save")
    public ResponseEntity<Chapter4Dto> saveChapter4Output(@PathVariable String sessionId) {
        Chapter4Dto savedDto = designInputService.saveChapter4Output(sessionId);
        return ResponseEntity.ok(savedDto);
    }

    @PutMapping("/{sessionId}/chapter4/update")
    public ResponseEntity<Chapter4Dto> updateChapter4(@PathVariable String sessionId, 
                    @RequestBody Chapter4Dto dto, 
                    @RequestParam(required = false, defaultValue = "false") boolean imported) {
        Chapter4Dto updatedDto = designInputService.updateChapter4(sessionId, dto, imported);
        return ResponseEntity.ok(updatedDto);
    }

    @GetMapping("/{sessionId}/chapter5")
    public ResponseEntity<Chapter5Dto> getChapter5(@PathVariable String sessionId) {
        Chapter5Dto dto = designInputService.getChapter5(sessionId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{sessionId}/chapter5/calculate")
    public ResponseEntity<Chapter5Dto> calculateChapter5Output(@PathVariable String sessionId) {
        Chapter5Dto calculatedDto = designInputService.calculateChapter5Output(sessionId);
        return ResponseEntity.ok(calculatedDto);
    }

    @PostMapping("/{sessionId}/chapter5/save")
    public ResponseEntity<Chapter5Dto> saveChapter5Output(@PathVariable String sessionId) {
        Chapter5Dto savedDto = designInputService.saveChapter5Output(sessionId);
        return ResponseEntity.ok(savedDto);
    }

    @PutMapping("/{sessionId}/chapter5/update")
    public ResponseEntity<Chapter5Dto> updateChapter5(@PathVariable String sessionId, 
                    @RequestBody Chapter5Dto dto, 
                    @RequestParam(required = false, defaultValue = "false") boolean imported) {
        Chapter5Dto updatedDto = designInputService.updateChapter5(sessionId, dto, imported);
        return ResponseEntity.ok(updatedDto);
    }
}
