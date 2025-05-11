package com.server.mechacal.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.mechacal.auth.entities.User;
import com.server.mechacal.auth.repositories.UserRepository;
import com.server.mechacal.dto.UserDto;
import com.server.mechacal.exceptions.UserNotFoundException;

@Service
public class UserInfoService {
    private final UserRepository userRepository;
    private final FileService fileService;
    private final PasswordEncoder passwordEncoder;

    @Value("${base.url}")
    private String baseUrl;

    public UserInfoService(UserRepository userRepository, FileService fileService, PasswordEncoder passwordEncoder) 
    {
        this.userRepository = userRepository;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto getInfo(String username) 
    {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UserNotFoundException("User not found!"));

        UserDto response = new UserDto();
        response.update(user);

        return response;
    }

    public UserDto getInfoId(String userId) 
    {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found!"));

        UserDto response = new UserDto();
        response.update(user);

        return response;
    }

    public UserDto updateUserInfo(UserDto userDto, MultipartFile file) throws IOException 
    {
        User user = userRepository.findById(userDto.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found!"));

        user.update(userDto);

        if (file != null) 
        {
            String fileId = fileService.uploadFile(file);
            user.setImageName(fileId);
        }

        userRepository.save(user);

        UserDto response = new UserDto();
        response.update(user);

        return response;
    }

    public User getEntityById(String userId) 
    {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
    
    public void changePassword(String username, String currentPassword, String newPassword) {
    User user = userRepository.findByEmail(username)
        .orElseThrow(() -> new UserNotFoundException("User not found!"));

    if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
        throw new RuntimeException("Current password is incorrect");
    }

    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
}


}
