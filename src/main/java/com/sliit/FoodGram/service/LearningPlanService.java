package com.sliit.FoodGram.service;

import com.sliit.FoodGram.model.LearningPlan;
import com.sliit.FoodGram.repository.LearningPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LearningPlanService {

    @Autowired
    private LearningPlanRepository learningPlanRepository;

    public List<LearningPlan> getPlansByUserId(String userId) {
        return learningPlanRepository.findByUserId(userId);
    }

    public LearningPlan createPlan(LearningPlan plan) {
        plan.setCreatedAt(LocalDateTime.now());
        return learningPlanRepository.save(plan);
    }

    public LearningPlan updatePlan(LearningPlan plan) {
        Optional<LearningPlan> existingPlan = learningPlanRepository.findById(plan.getId());
        if (existingPlan.isPresent()) {
            LearningPlan updatedPlan = existingPlan.get();
            updatedPlan.setTitle(plan.getTitle());
            updatedPlan.setDescription(plan.getDescription());
            updatedPlan.setUserId(plan.getUserId());
            return learningPlanRepository.save(updatedPlan);
        } else {
            throw new RuntimeException("Learning plan not found");
        }
    }

    public void deletePlan(String id, String userId) {
        Optional<LearningPlan> plan = learningPlanRepository.findById(id);
        if (plan.isPresent() && plan.get().getUserId().equals(userId)) {
            learningPlanRepository.deleteById(id);
        } else {
            throw new RuntimeException("Learning plan not found or unauthorized");
        }
    }
}