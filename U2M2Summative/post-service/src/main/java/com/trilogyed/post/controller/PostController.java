package com.trilogyed.post.controller;

import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.exception.NotFoundException;
import com.trilogyed.post.model.Post;
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
@CacheConfig(cacheNames = {"posts"})
public class PostController {

    @Autowired
    PostDao postDao;

    // adds the return value of the method to the cache using post id as the key
    //handles requests to add a post
    @CachePut(key = "#result.getPostId()")
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Post addPost(@RequestBody @Valid Post post) {
        return postDao.addPost(post);
    }

    // caches the result of the method
    // it automatically uses id as the key
    // handles requests to retrieve a post by post id
    @Cacheable
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Post getPost(@PathVariable int id) {
        Post post = postDao.getPost(id);
        if (post == null)
            throw new NotFoundException("Post could not be retrieved for id " + id);
        return post;
    }

    // removes post with given post id as the key from the cache
    // handles requests to update a post with a matching id
    @CacheEvict(key = "#post.getPostId()")
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

    // removes post with given post id as the key from the cache
    // handles requests to delete a post by id
    @CacheEvict
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) {
        postDao.deletePost(id);
    }

    // didn't cache b/c result would change frequently as users make more posts
    // handles requests to retrieve all posts with a matching poster name
    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByPosterName(@PathVariable("poster_name") String posterName) {
        List<Post> posts = postDao.getPostsByPosterName(posterName);
        return posts;
    }

}

