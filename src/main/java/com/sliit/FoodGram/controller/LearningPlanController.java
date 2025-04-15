package com.sliit.FoodGram.controller;

import com.sliit.FoodGram.model.LearningPlan;
import com.sliit.FoodGram.repository.LearningPlanRepository;
import com.sliit.FoodGram.service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/learning-plans")
public class LearningPlanController {

    @Autowired
    private LearningPlanService learningPlanService;
    @Autowired
    private LearningPlanRepository learningPlanRepository;

    @GetMapping
    public ResponseEntity<List<LearningPlan>> getUserPlans(Authentication authentication) {
        String userId = authentication.getName();
        List<LearningPlan> plans = learningPlanService.getPlansByUserId(userId);
        return ResponseEntity.ok(plans);
    }

    @PostMapping
    public ResponseEntity<LearningPlan> createPlan(
            @RequestBody LearningPlan plan
    ) {
        String userId = plan.getId();
        plan.setUserId(userId);
        LearningPlan createdPlan = learningPlanService.createPlan(plan);
        return ResponseEntity.ok(createdPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningPlan> updatePlan(
            @PathVariable String id,
            @RequestBody LearningPlan plan
    ) {
        String userId = plan.getId();
        plan.setId(id);
        plan.setUserId(userId);
        LearningPlan updatedPlan = learningPlanService.updatePlan(plan);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable String id) {
        // Call the repository to delete the plan by its ID
        if (learningPlanRepository.existsById(id)) {
            learningPlanRepository.deleteById(id); // Deleting the plan from MongoDB
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if the plan is not found
        }
    }

}