package com.sliit.FoodGram.service.Impl;

import com.sliit.FoodGram.model.Profile;
import com.sliit.FoodGram.repository.ProfileRepository;
import com.sliit.FoodGram.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfileByUsername(String username) {
        return profileRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Profile getProfileByUserId(String userId) {
        return profileRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(String username, Profile updatedProfile) {
        Optional<Profile> existingProfileOpt = profileRepository.findByUsername(username);
        if (existingProfileOpt.isPresent()) {
            Profile existingProfile = existingProfileOpt.get();
            existingProfile.setFullName(updatedProfile.getFullName());
            existingProfile.setBio(updatedProfile.getBio());
            existingProfile.setProfilePictureUrl(updatedProfile.getProfilePictureUrl());
            existingProfile.setEmail(updatedProfile.getEmail());
            return profileRepository.save(existingProfile);
        }
        return null;
    }

    @Override
    public Profile updateProfileByUserId(String userId, Profile updatedProfile) {
        Optional<Profile> existingProfileOpt = profileRepository.findByUserId(userId);
        if (existingProfileOpt.isPresent()) {
            Profile existingProfile = existingProfileOpt.get();
            existingProfile.setFullName(updatedProfile.getFullName());
            existingProfile.setBio(updatedProfile.getBio());
            existingProfile.setProfilePictureUrl(updatedProfile.getProfilePictureUrl());
            existingProfile.setEmail(updatedProfile.getEmail());
            return profileRepository.save(existingProfile);
        }
        return null;
    }

    @Override
    public Optional<Profile> getProfile(String id) {
        return profileRepository.findById(id);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(String username) {
        Optional<Profile> profileOpt = profileRepository.findByUsername(username);
        profileOpt.ifPresent(profileRepository::delete);
    }

    @Override
    public void deleteProfileByUserId(String userId) {
        Optional<Profile> profileOpt = profileRepository.findByUserId(userId);
        profileOpt.ifPresent(profileRepository::delete);
    }
}