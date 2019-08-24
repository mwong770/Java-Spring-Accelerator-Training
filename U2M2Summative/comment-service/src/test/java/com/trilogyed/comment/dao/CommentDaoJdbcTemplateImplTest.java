package com.trilogyed.comment.dao;

import com.trilogyed.comment.exception.NotFoundException;
import com.trilogyed.comment.model.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAsync
public class CommentDaoJdbcTemplateImplTest {

    @Autowired
    CommentDao commentDao;

    private JdbcTemplate jdbcTemplate;

    // clear comment table in database
    @Before
    public void setUp() throws Exception {
        List<Comment> comments = commentDao.getAllComments();
        for (Comment c : comments) {
            commentDao.deleteComment(c.getCommentId());
        }
    }

    // tests addComment(), getComment() and deleteComment()
    @Test
    public void addGetDeleteComment() {

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("FeelingGood");
        comment.setComment("I have one. if (sad() == true) {sad.stop(); beAwesome();}");

        comment = commentDao.addComment(comment);

        Comment comment1 = commentDao.getComment(comment.getCommentId());

        assertEquals(comment, comment1);

        commentDao.deleteComment(comment.getCommentId());
        comment1 = commentDao.getComment(comment.getCommentId());
        assertNull(comment1);
    }

    // tests if will return null if try to get comment with non-existent id
    @Test
    public void getCommentWithNonExistentId() {
        Comment comment = commentDao.getComment(5);
        assertNull(comment);
    }

    // tests updateComment()
    @Test
    public void updateComment() {
        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("Javian");
        comment.setComment("I have one. if (sad() == true) {sad.stop();}");

        comment = commentDao.addComment(comment);

        comment.setComment("I have one. if (sad() == true) {sad.stop(); beAwesome();}");
        commentDao.updateComment(comment);

        Comment comment1 = commentDao.getComment(comment.getCommentId());
        assertEquals(comment1, comment);
    }

    // tests if will throw exception if id provided does not exist when trying to update comment
    @Test(expected  = IllegalArgumentException.class)
    public void updateWithIllegalArgumentException() {

        Comment comment = new Comment();
        comment.setCommentId(2);
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("Javian");
        comment.setComment("That's not funny!");

        commentDao.updateComment(comment);

    }

    // tests if will throw exception if id provided does not exist when trying to delete comment
    @Test(expected  = NotFoundException.class)
    public void deleteCommentWithNonExistentId() {

        commentDao.deleteComment(2);

    }

    // tests getAllComments()
    @Test
    public void getAllComments() {
        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("Javian");
        comment.setComment("I have one. if (sad() == true) {sad.stop();}");

        commentDao.addComment(comment);

        comment = new Comment();
        comment.setPostId(2);
        comment.setCreateDate(LocalDate.of(2019, 8, 15));
        comment.setCommenterName("CoderLife");
        comment.setComment("Stealing!!!");

        commentDao.addComment(comment);

        List<Comment> comments = commentDao.getAllComments();
        assertEquals(2, comments.size());
    }

    // tests getCommentsByPostId()
    @Test
    public void getCommentsByPostId() {

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("Javian");
        comment.setComment("I have one. if (sad() == true) {sad.stop();}");

        commentDao.addComment(comment);

        comment = new Comment();
        comment.setCommentId(2);
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("Javian");
        comment.setComment("That's not funny!");

        commentDao.addComment(comment);

        comment = new Comment();
        comment.setPostId(2);
        comment.setCreateDate(LocalDate.of(2019, 8, 15));
        comment.setCommenterName("CoderLife");
        comment.setComment("Stealing!!!");

        commentDao.addComment(comment);

        List<Comment> posts = commentDao.getCommentsByPostId(1);
        assertEquals(2, posts.size());
    }

    // tests deleteCommentsByPostId()
    @Test
    public void deleteCommentsByPostId(){

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019, 8, 16));
        comment.setCommenterName("FeelingGood");
        comment.setComment("I have one. if (sad() == true) {sad.stop(); beAwesome();}");

        commentDao.addComment(comment);

        List<Comment> comments = commentDao.getCommentsByPostId(1);

        // shows successfully entered comment with post id of 1 before attempting to delete
        assertEquals(1, comments.size());

        commentDao.deleteCommentsByPostId(1);

        comments = commentDao.getCommentsByPostId(1);

        // shows successfully deleted comments with post id of 1
        assertEquals(0, comments.size());

    }

}
