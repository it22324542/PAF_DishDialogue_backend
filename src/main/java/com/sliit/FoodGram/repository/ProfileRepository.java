package com.sliit.FoodGram.repository;

import com.sliit.FoodGram.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByUsername(String username);
    Optional<Profile> findByUserId(String userId);
}
