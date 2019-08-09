package com.trilogyed.post.controller;

import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.exception.NotFoundException;
import com.trilogyed.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class PostController {

    @Autowired
    PostDao postDao;

    //handles requests to add a post
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Post addPost(@RequestBody @Valid Post post) {
        return postDao.addPost(post);
    }

    // handles requests to retrieve a post by post id
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Post getPost(@PathVariable int id) {
        Post post = postDao.getPost(id);
        if (post == null)
            throw new NotFoundException("Post could not be retrieved for id " + id);
        return post;
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
        postDao.updatePost(post);
    }

    // handles requests to delete a post by id
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) {
        postDao.deletePost(id);
    }

    // handles requests to retrieve all posts with a matching poster name
    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByPosterName(@PathVariable("poster_name") String posterName) {
        List<Post> posts = postDao.getPostsByPosterName(posterName);
        return posts;
    }

}

