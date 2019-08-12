package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.util.feign.CommentClient;
import com.trilogyed.stwitter.util.messages.Comment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class CommentService {

    @Autowired
    private CommentClient commentClient;

    public static final String EXCHANGE = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.#";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // constructors

    public CommentService() {
    }

    public CommentService(CommentClient commentClient, RabbitTemplate rabbitTemplate) {
        this.commentClient = commentClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    // rabbit
    public String addCommentViaQueue(@RequestBody Comment comment) {

            // create message to send queue
            Comment msg = new Comment(comment.getCommentId(), comment.getPostId(), comment.getCreateDate(), comment.getCommenterName(), comment.getComment());

            // sends message to topic exchange
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);

            return "Comment Sent";

    }

    // Returns Comment and not CommentViewModel b/c the response does not include post data
    // Including the post id in the response acts as confirmation even though the post id is in the request
    public Comment getComment(int id) {
        return commentClient.getComment(id);
    }

    public void updateComment(int id, Comment comment) {
        commentClient.updateComment(id, comment);
    }

    public void deleteComment(int id) {
        commentClient.deleteComment(id);
    }

    // Returns Comment and not CommentViewModel b/c the response does not include post data
    // Including the post id in the response acts as confirmation even though the post id is in the request
    public List<Comment> getCommentsByPostId(int id) {
        return commentClient.getCommentsByPostId(id);
    }

    public void deleteCommentByPostId(int postId) {
        commentClient.deleteCommentByPostId(postId);
    }

}
