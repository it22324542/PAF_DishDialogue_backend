package com.sliit.FoodGram.controller;

import com.sliit.FoodGram.model.Story;
import com.sliit.FoodGram.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/stories")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping
    public ResponseEntity<List<Story>> getAllStories() {
        try {
            List<Story> stories = storyService.getStories();
            return ResponseEntity.ok(stories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createStory(@Valid @RequestBody Story story) {
        try {
            story.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            story.setExpiresAt(LocalDateTime.now().plusHours(24).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            Story createdStory = storyService.createStory(story);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Failed to create story: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStory(@PathVariable String id, @Valid @RequestBody Story story) {
        try {
            Story existingStory = storyService.getStoryById(id);
            if (existingStory == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Story not found"));
            }

            // Skip user check for now
            existingStory.setContent(story.getContent());
            existingStory.setImageUrl(story.getImageUrl());
            Story updatedStory = storyService.updateStory(existingStory);
            return ResponseEntity.ok(updatedStory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Failed to update story: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable String id) {
        try {
            Story existingStory = storyService.getStoryById(id);
            if (existingStory == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Story not found"));
            }
            storyService.deleteStory(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Failed to delete story: " + e.getMessage()));
        }
    }

    @PatchMapping("/{id}/view")
    public ResponseEntity<?> viewStory(@PathVariable String id, @RequestBody Map<String, String> payload) {
        try {
            String viewerId = payload.get("viewerId");
            if (viewerId == null || viewerId.isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("viewerId is required"));
            }
            Story story = storyService.getStoryById(id);
            if (story == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Story not found"));
            }
            List<String> viewedBy = story.getViewedBy();
            if (!viewedBy.contains(viewerId)) {
                viewedBy.add(viewerId);
                story.setViewedBy(viewedBy);
                storyService.updateStory(story);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal server error: " + e.getMessage()));
        }
    }

    static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}