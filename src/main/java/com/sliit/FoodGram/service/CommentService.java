package com.sliit.FoodGram.service;

import com.sliit.FoodGram.model.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);

    List<Comment> getComments(String postId);

    Comment updateComment(Comment comment);

    void deleteComment(String id);
}
