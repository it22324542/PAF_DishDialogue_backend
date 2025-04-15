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
    private CommentService commentService;

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/{postId}")
    public List<Comment> getComments(@PathVariable String postId) {
        return commentService.getComments(postId);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@RequestBody Comment comment, @PathVariable String id) {
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
    }
}