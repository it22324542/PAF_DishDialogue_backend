package com.sliit.FoodGram.controller;

import com.sliit.FoodGram.model.Profile;
import com.sliit.FoodGram.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/by-userid/{userId}")
    public Profile getProfileByUserId(@PathVariable String userId) {
        return profileService.getProfileByUserId(userId);
    }

    @GetMapping("/{username}")
    public Profile getProfileByUsername(@PathVariable String username) {
        return profileService.getProfileByUsername(username);
    }

    @PostMapping
    public Profile createProfile(@RequestBody Profile profile) {
        return profileService.createProfile(profile);
    }

    // Updated to use userId instead of username
    @PutMapping("/by-userid/{userId}")
    public Profile updateProfile(@PathVariable String userId, @RequestBody Profile updatedProfile) {
        return profileService.updateProfileByUserId(userId, updatedProfile);
    }

    // Updated to use userId instead of username
    @DeleteMapping("/by-userid/{userId}")
    public void deleteProfile(@PathVariable String userId) {
        profileService.deleteProfileByUserId(userId);
    }
}