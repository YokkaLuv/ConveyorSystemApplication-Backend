package com.server.mechacal.repositories;

import com.server.mechacal.entities.DesignInput;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DesignInputRepository extends MongoRepository<DesignInput, String> {}
