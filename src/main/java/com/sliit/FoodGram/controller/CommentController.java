package com.sliit.FoodGram.controller;


import com.sliit.FoodGram.model.Comment;
import com.sliit.FoodGram.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService; // Handles business logic for comment operations


    @PostMapping // Handles HTTP POST requests to "/api/comments"
    public Comment addComment(@RequestBody Comment comment) { //created comment
        return commentService.addComment(comment);
    }

    @GetMapping("/{postId}")  // Handles HTTP GET requests to "/api/comments/{postId}"
    public List<Comment> getComments(@PathVariable String postId) {
        return commentService.getComments(postId);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@RequestBody Comment comment, @PathVariable String id) { //update comment
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable String id) { //delete comment
        commentService.deleteComment(id);
    }
}