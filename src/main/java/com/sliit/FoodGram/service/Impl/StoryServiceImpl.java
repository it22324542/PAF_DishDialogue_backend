package com.sliit.FoodGram.service.Impl;

import com.sliit.FoodGram.model.Story;
import com.sliit.FoodGram.repository.StoryRepository;
import com.sliit.FoodGram.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public Story createStory(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public List<Story> getStories() {
        return storyRepository.findAll();
    }

    @Override
    public Story updateStory(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public void deleteStory(String id) {
        storyRepository.deleteById(id);
    }

    public Story getStoryById(String id) {
        Optional<Story> story = storyRepository.findById(id);
        return story.orElse(null);
    }
    public void markStoryAsViewed(String storyId, String viewerId) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        List<String> viewedBy = story.getViewedBy();
        if (!viewedBy.contains(viewerId)) {
            viewedBy.add(viewerId);
            story.setViewedBy(viewedBy);
            storyRepository.save(story);
        }
    }

}