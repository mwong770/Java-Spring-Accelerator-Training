package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.util.feign.CommentClient;
import com.trilogyed.stwitter.util.messages.Comment;
import com.trilogyed.stwitter.util.messages.Post;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentClient commentClient;

    @Mock
    private RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.#";

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        // configure mock objects
        setUpCommentClientMock();

        // Passes mock objects
        commentService = new CommentService(commentClient, rabbitTemplate);

    }

    //tests addComment()
    @Test
    public void addComment() {

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("Javian");
        comment.setComment("I have one. if (sad() == true) {sad.stop();}");

        // addCommentViaQueue() returns "Comment Sent" or null
        String response = commentService.addCommentViaQueue(comment);

        // checks if method was called with the indicated arguments
        verify(rabbitTemplate).convertAndSend(EXCHANGE, ROUTING_KEY, comment);

        assertEquals("Comment Sent", response);

    }

    //tests getComment()
    @Test
    public void getComment() {

        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("Javian");
        comment.setComment("I have one. if (sad() == true) {sad.stop();}");

        Comment comment1 = commentService.getComment(comment.getCommentId());

        assertEquals(comment, comment1);
    }

    // tests if will return null if try to get comment with non-existent id
    @Test
    public void getCommentWithNonExistentId() {
        Comment comment = commentService.getComment(500);
        assertNull(comment);
    }

    // tests getCommentsByPostId()
    @Test
    public void getCommentsByPostId() {

        List<Comment> commentsForPost1 = new ArrayList<>();

        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("Javian");
        comment.setComment("I have one. if (sad() == true) {sad.stop();}");

        commentsForPost1.add(comment);

        Comment comment2 = new Comment();
        comment2.setCommentId(2);
        comment2.setPostId(1);
        comment2.setCreateDate(LocalDate.of(2019, 8, 15));
        comment2.setCommenterName("CoderLife");
        comment2.setComment("Stealing!!!");

        commentsForPost1.add(comment2);

        List<Comment> comments = commentService.getCommentsByPostId(1);

        assertEquals(commentsForPost1, comments);

        List<Comment> commentsForPost2 = new ArrayList<>();

        Comment comment3 = new Comment();
        comment3.setCommentId(3);
        comment3.setPostId(2);
        comment3.setCreateDate(LocalDate.of(2019, 8, 16));
        comment3.setCommenterName("Javian");
        comment3.setComment("That's not funny!");

        commentsForPost2.add(comment3);

        comments = commentService.getCommentsByPostId(2);

        assertEquals(commentsForPost2, comments);

    }

    // tests deleteComment()
    @Test
    public void deleteComment() {
        // get the comment you want to delete using the id you will use in deleteComment()
        Comment comment = commentService.getComment(1);
        // call method testing
        commentService.deleteComment(1);
        // create ArgumentCaptor instance for Integer class
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        //verify if method call happened and capture the arguments
        verify(commentClient).deleteComment(argument.capture());
        // check if the id of the comment want to delete matches the id passed to deleteComment()
        assertEquals(comment.getCommentId(), argument.getValue().intValue());
    }

    // tests updateComment()
    @Test
    public void updatePost() {

        Comment comment = new Comment();
        comment.setCommentId(2);
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 15));
        comment.setCommenterName("CodersLife");// Instead of CoderLife
        comment.setComment("Stealing!!!");

        // call method testing
        commentService.updateComment(1, comment);
        // create ArgumentCaptor instance for Comment class
        ArgumentCaptor<Comment> argument = ArgumentCaptor.forClass(Comment.class);
        //verify if method call happened and capture the comment argument
        verify(commentClient).updateComment(any(Integer.class), argument.capture());
        // check if the updated commentName of the comment want to change
        // matches the commentName passed to the method
        assertEquals(comment.getCommenterName(), argument.getValue().getCommenterName());

    }

    // tests deleteCommentByPostId()
    @Test
    public void deleteCommentByPostId() {
        // get all the comments with the id you want to delete
        List<Comment> comments = commentService.getCommentsByPostId(1);
        // call the method with the id of the comments you want to delete
        commentService.deleteCommentByPostId(1);
        // create an instance of ArgumentCaptor for Integer class
        ArgumentCaptor<Integer> postCaptor = ArgumentCaptor.forClass(Integer.class);
        // very the method call happened and capture the argument
        verify(commentClient).deleteCommentByPostId(postCaptor.capture());
        // check if the comment id of the first comment in the list of comments you want to delete
        // matches the comment id passed to the method
        assertEquals(comments.get(0).getCommentId(), postCaptor.getValue().intValue());
    }

    // tests default constructor for test coverage
    // so developers know something went wrong if less than 100%
    @Test
    public void createADefaultComment() {

        Object commentObj = new CommentService();

        assertEquals(true , commentObj instanceof CommentService);

    }

    // create mocks

    public void setUpCommentClientMock() {

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("Javian");
        comment.setComment("I have one. if (sad() == true) {sad.stop();}");

        Comment comment2 = new Comment();
        comment2.setCommentId(1);
        comment2.setPostId(1);
        comment2.setCreateDate(LocalDate.of(2019, 8, 16));
        comment2.setCommenterName("Javian");
        comment2.setComment("I have one. if (sad() == true) {sad.stop();}");

        Comment comment3 = new Comment();
        comment3.setPostId(1);
        comment3.setCreateDate(LocalDate.of(2019, 8, 15));
        comment3.setCommenterName("CoderLife");
        comment3.setComment("Stealing!!!");

        Comment comment4 = new Comment();
        comment4.setCommentId(2);
        comment4.setPostId(1);
        comment4.setCreateDate(LocalDate.of(2019, 8, 15));
        comment4.setCommenterName("CoderLife");
        comment4.setComment("Stealing!!!");

        Comment comment5 = new Comment();
        comment5.setPostId(2);
        comment5.setCreateDate(LocalDate.of(2019, 8, 16));
        comment5.setCommenterName("Javian");
        comment5.setComment("That's not funny!");

        Comment comment6 = new Comment();
        comment6.setCommentId(3);
        comment6.setPostId(2);
        comment6.setCreateDate(LocalDate.of(2019, 8, 16));
        comment6.setCommenterName("Javian");
        comment6.setComment("That's not funny!");

        doReturn(comment2).when(commentClient).addComment(comment);
        doReturn(comment4).when(commentClient).addComment(comment3);
        doReturn(comment6).when(commentClient).addComment(comment5);

        doReturn(comment2).when(commentClient).getComment(1);

        List<Comment> post1List = new ArrayList<>();
        post1List.add(comment2);
        post1List.add(comment4);

        List<Comment> post2List = new ArrayList<>();

        post2List.add(comment6);

        List<Post> emptyList = new ArrayList<>();

        doReturn(post1List).when(commentClient).getCommentsByPostId(1);
        doReturn(post2List).when(commentClient).getCommentsByPostId(2);
        doReturn(emptyList).when(commentClient).getCommentsByPostId(3);

    }

}
