package com.sliit.FoodGram.service;

import com.sliit.FoodGram.model.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<Profile> getAllProfiles();
    Profile getProfileByUsername(String username);
    Profile getProfileByUserId(String userId);
    Profile createProfile(Profile profile);
    Profile updateProfile(String username, Profile updatedProfile);
    Profile updateProfileByUserId(String userId, Profile updatedProfile); // New method
    Optional<Profile> getProfile(String id);
    Profile updateProfile(Profile profile);
    void deleteProfile(String username);
    void deleteProfileByUserId(String userId); // New method
}