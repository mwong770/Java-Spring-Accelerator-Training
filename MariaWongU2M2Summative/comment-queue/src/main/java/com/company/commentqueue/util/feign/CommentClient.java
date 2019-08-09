package com.company.commentqueue.util.feign;

import com.company.commentqueue.util.messages.Comment;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.openfeign.EnableFeignClients;

@FeignClient(name = "comment-service")
public interface CommentClient {

    // request to create comment
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    Comment addComment(@RequestBody Comment comment);
}
