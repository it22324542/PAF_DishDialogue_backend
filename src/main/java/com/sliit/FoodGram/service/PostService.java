package com.sliit.FoodGram.service;

import com.sliit.FoodGram.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);

    List<Post> getPosts();

    Optional<Post> getPost(String id);

    Post updatePost(Post post);

    void deletePost(String id);
}
