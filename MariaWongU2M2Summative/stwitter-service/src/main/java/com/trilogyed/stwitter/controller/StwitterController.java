package com.trilogyed.stwitter.controller;

import com.trilogyed.stwitter.service.CommentService;
import com.trilogyed.stwitter.service.PostService;
import com.trilogyed.stwitter.util.messages.Comment;
import com.trilogyed.stwitter.util.messages.Post;
import com.trilogyed.stwitter.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
// specifies the name of the cache our code will use
@CacheConfig(cacheNames = {"stwitter"})
public class StwitterController {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    //constructors

    public StwitterController() {
    }

    public StwitterController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    // *** POST ROUTES ***

    // Posts were not cached here because the response includes comments
    // that can change very frequently
    // Posts are cached in the post service

    // handles requests to retrieve a post by post id
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel getPost(@PathVariable int id) {
        Post post = postService.getPost(id);

        return postService.getPost(id);
    }

    //handles requests to retrieve all posts with a matching poster name
    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PostViewModel> getPostsByPosterName(@PathVariable("poster_name") String posterName) {
        return postService.getPostsByPosterName(posterName);
    }

    //handles requests to add a post
    //returns PostViewModel instead of Post despite no comments for ui consistency
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public PostViewModel addPost(@RequestBody @Valid Post post) {
        return postService.addPost(post);
    }

    // handles requests to update a post with a matching id
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable int id, @RequestBody @Valid Post post) {
        if (post.getPostId() == 0)
            post.setPostId(id);
        if (id != post.getPostId()) {
            throw new IllegalArgumentException("ID on path must match the ID in the Post object");
        }
        postService.updatePost(id, post);
    }

    // handles requests to delete a post by id
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) {
        postService.deletePost(id);
    }

    // *** COMMENT ROUTES ***

    // Comments were cached even though the comment service implements cache
    // to prevent running methods here when in cache and to not be dependent
    // on the comment services implementing cache

    // didn't cache b/c returns string, not object with comment id since going through queueu
    // handles requests to add a comment - goes to queue
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addComment(@RequestBody @Valid Comment comment) {
        return commentService.addCommentViaQueue(comment);
    }

    // caches the result of the method - it automatically uses id as the key
    // handles requests to retrieve a comment by comment id
    @Cacheable
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Comment getComment(@PathVariable int id) {
        return commentService.getComment(id);
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
            throw new IllegalArgumentException("ID on path must match the ID in the Post object");
        }
        commentService.updateComment(id, comment);
    }

    // removes comment with given comment id as the key from the cache
    // handles requests to delete a comment by id
    @CacheEvict
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
    }

}

