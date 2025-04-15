package com.sliit.FoodGram.service;

import com.sliit.FoodGram.model.Story;
import java.util.List;

public interface StoryService {
    Story createStory(Story story);
    List<Story> getStories();
    Story updateStory(Story story);
    void deleteStory(String id);
    Story getStoryById(String id);
}