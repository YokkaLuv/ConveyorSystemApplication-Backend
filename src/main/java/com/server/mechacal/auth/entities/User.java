package com.server.mechacal.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.server.mechacal.dto.UserDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@Document(collection = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class User implements UserDetails 
{
    @Id
    private String userId;
    
    private String name;

    private String phoneNumber;

    private String imageName;

    private String username;

    @Builder.Default
    private List<String> sessionIds = new ArrayList<>();

    // @Builder.Default
    // private List<String> linkedStudentIds = new ArrayList<>();
    
    @NotBlank(message = "The email field can't be blank")
    @Indexed(unique = true)
    @Email(message = "Please enter email in proper format!")
    private String email;
    
    @NotBlank(message = "The password field can't be blank")
    @Size(min = 8, message = "The password must have at least 5 characters")
    private String password;

    @DocumentReference
    private RefreshToken refreshToken;

    private UserRole role;

    @Builder.Default 
    private boolean isEnabled = true;

    @Builder.Default 
    private boolean isAccountNonExpired = true;

    @Builder.Default 
    private boolean isAccountNonLocked = true;

    @Builder.Default 
    private boolean isCredentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return email;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled()
    {
        return isEnabled;
    }

    public void update(UserDto dto) {
        if (dto.getName() != null) name = dto.getName();
        if (dto.getPhoneNumber() != null) phoneNumber = dto.getPhoneNumber();
        if (dto.getRole() != null) role = dto.getRole();
        if (dto.getSessionIds() != null) sessionIds = dto.getSessionIds();
        // if (dto.getLinkedStudentIds() != null) linkedStudentIds = dto.getLinkedStudentIds();
    }
}