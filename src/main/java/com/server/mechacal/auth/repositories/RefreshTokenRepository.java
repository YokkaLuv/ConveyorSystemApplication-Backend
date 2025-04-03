package com.server.mechacal.auth.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.mechacal.auth.entities.RefreshToken;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}