package com.trilogyed.comment.controller;

import com.trilogyed.comment.dao.CommentDao;
import com.trilogyed.comment.exception.NotFoundException;
import com.trilogyed.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@CacheConfig(cacheNames = {"comments"})
public class CommentController {

    @Autowired
    CommentDao commentDao;

    // adds the return value of the method to the cache using comment id as the key
    //handles requests to add a comment
    @CachePut(key = "#result.getCommentId()")
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@RequestBody @Valid Comment comment) {
        return commentDao.addComment(comment);
    }

    // caches the result of the method
    // it automatically uses id as the key
    // handles requests to retrieve a comment by comment id
    @Cacheable
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Comment getComment(@PathVariable int id) {
        Comment comment = commentDao.getComment(id);
        if (comment == null)
            throw new NotFoundException("Comment could not be retrieved for id " + id);
        return comment;
    }

    // removes comment with given comment id as the key from the cache
    // handles requests to update a comment with a matching id
    @CacheEvict(key = "#comment.getCommentId()")
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable int id, @RequestBody @Valid Comment comment) {
        if (comment.getCommentId() == 0)
            comment.setCommentId(id);
        if (id != comment.getCommentId()) {
            throw new IllegalArgumentException("ID on path must match the ID in the Comment object");
        }
        commentDao.updateComment(comment);
    }

    // removes comment with given comment id as the key from the cache
    // handles requests to delete a comment by id
    @CacheEvict
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int id) {
        commentDao.deleteComment(id);
    }

    // did not cache because results will change frequently
    // handles requests to retrieve all comments with a matching post id
    @RequestMapping(value = "/comments/post/{postId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getCommentsByPostId(@PathVariable int postId) {
        List<Comment> comments = commentDao.getCommentsByPostId(postId);
        return comments;
    }

    // no need to remove cache because never cached
    // handles requests to delete a comment by id
    @RequestMapping(value = "/comments/post/{postId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentsByPostId(@PathVariable int postId) {
        commentDao.deleteCommentsByPostId(postId);
    }

}
