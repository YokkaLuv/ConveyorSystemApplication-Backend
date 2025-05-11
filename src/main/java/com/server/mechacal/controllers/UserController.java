package com.server.mechacal.controllers;

import com.server.mechacal.dto.UserDto;
import com.server.mechacal.services.UserInfoService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserInfoService userInfoService;

    @GetMapping("/getInfo")
    public ResponseEntity<UserDto> fetchUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userInfoService.getInfo(userDetails.getUsername()));
    }

    @GetMapping("/getInfoId/{userId}")
    public ResponseEntity<UserDto> fetchUserInfoId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userInfoService.getInfoId(userId));
    }

    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUserInfo(@RequestPart(value  = "image", required = false) MultipartFile file, 
                                                        @RequestPart("user") UserDto userDto) throws IOException {
        return ResponseEntity.ok(userInfoService.updateUserInfo(userDto, file));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
            
        userInfoService.changePassword(userDetails.getUsername(), oldPassword, newPassword);
        return ResponseEntity.ok("Password changed successfully");
    }

}