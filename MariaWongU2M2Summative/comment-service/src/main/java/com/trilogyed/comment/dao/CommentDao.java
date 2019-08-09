package com.trilogyed.comment.dao;

import com.trilogyed.comment.model.Comment;

import java.util.List;

public interface CommentDao {

    // standard CRUD

    Comment addComment(Comment comment);

    Comment getComment(int id);

    void updateComment(Comment comment);

    void deleteComment(int id);

    // additional methods

    List<Comment> getCommentsByPostId(int postId);

    void deleteCommentByPostId(int postId);

    // needed for testing

    List<Comment> getAllComments();

}
