package com.company.commentqueue;

import com.company.commentqueue.util.feign.CommentClient;
import com.company.commentqueue.util.messages.Comment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    private final CommentClient client;

    public MessageListener(CommentClient client) {
        this.client = client;
    }

    @RabbitListener(queues = CommentQueueApplication.QUEUE_NAME)
    public void receiveComment(Comment comment) {

            client.addComment(comment);

    }

}

