package com.server.mechacal.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.mechacal.entities.WorkSession;

public interface WorkSessionRepository extends MongoRepository<WorkSession, String> {}
