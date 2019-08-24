package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.util.messages.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "comment-service")
public interface CommentClient {

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    Comment addComment(@RequestBody @Valid Comment comment);

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    Comment getComment(@PathVariable int id);

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    void updateComment(@PathVariable int id, @RequestBody @Valid Comment comment);

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    void deleteComment(@PathVariable int id);

    @RequestMapping(value = "/comments/post/{id}", method = RequestMethod.GET)
    List<Comment> getCommentsByPostId(@PathVariable int id);

    @RequestMapping(value = "/comments/post/{postId}", method = RequestMethod.DELETE)
    void deleteCommentByPostId(@PathVariable int postId);
}