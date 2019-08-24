package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.util.messages.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "post-service")
public interface PostClient {

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    Post addPost(@RequestBody @Valid Post post);

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    Post getPost(@PathVariable int id);

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    void updatePost(@PathVariable int id, @RequestBody @Valid Post post);

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    void deletePost(@PathVariable int id);

    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    List<Post> getPostsByPosterName(@PathVariable("poster_name") String posterName);
}

