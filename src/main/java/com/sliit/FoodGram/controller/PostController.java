

package com.sliit.FoodGram.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sliit.FoodGram.model.Post;
import com.sliit.FoodGram.service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;


    // create post
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
// Get all posts
    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }
// Get a single post by ID
    @GetMapping("/{id}")
    public Post getPost(@PathVariable String id) {
        return postService.getPost(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

// Update an existing post by ID
    @PutMapping("/{id}")
    public Post updatePost(@RequestBody Post post, @PathVariable String id) {
        return postService.updatePost(post);
    }
// Delete a post by ID
   
}
