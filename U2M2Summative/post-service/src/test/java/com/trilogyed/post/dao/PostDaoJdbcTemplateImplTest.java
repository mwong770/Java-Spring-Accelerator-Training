package com.trilogyed.post.dao;

import com.trilogyed.post.exception.NotFoundException;
import com.trilogyed.post.model.Post;
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
public class PostDaoJdbcTemplateImplTest {

    @Autowired
    PostDao postDao;

    private JdbcTemplate jdbcTemplate;

    // clear post table in database
    @Before
    public void setUp() throws Exception {
        List<Post> posts = postDao.getAllPosts();
        for (Post p : posts) {
            postDao.deletePost(p.getPostId());
        }
    }

    // tests addPost(), getPost() and deletePost()
    @Test
    public void addGetDeletePost() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("JavaHumorist");
        post.setPost("What do computers and air conditioners have in common? They both become useless when you open Windows.");

        post = postDao.addPost(post);

        Post post1 = postDao.getPost(post.getPostId());

        assertEquals(post, post1);

        postDao.deletePost(post.getPostId());
        post1 = postDao.getPost(post.getPostId());
        assertNull(post1);
    }

    // tests if will return null if try to get post with non-existent id
    @Test
    public void getPostWithNonExistentId() {
        Post post = postDao.getPost(5);
        assertNull(post);
    }

    // tests updatePost()
    @Test
    public void updatePost() {
        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("JavaHumorist");
        post.setPost("What is the object-oriented way to become wealthy? Inheritance");

        post = postDao.addPost(post);

        post.setPost("What do computers and air conditioners have in common? They both become useless when you open Windows.");
        postDao.updatePost(post);

        Post post1 = postDao.getPost(post.getPostId());
        assertEquals(post1, post);
    }

    // tests if will throw exception if id provided does not exist when trying to update post
    @Test(expected  = IllegalArgumentException.class)
    public void updateWithIllegalArgumentException() {

        Post post = new Post();
        post.setPostId(2);
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("CodeJoker");
        post.setPost("What is a programmer's favorite hangout place? Foo Bar");

        postDao.updatePost(post);

    }

    // tests if will throw exception if id provided does not exist when trying to delete post
    @Test(expected  = NotFoundException.class)
    public void deletePostWithNonExistentId() {

        postDao.deletePost(2);

    }

    // tests getAllPosts()
    @Test
    public void getAllPosts() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("JavaHumorist");
        post.setPost("What is the object-oriented way to become wealthy? Inheritance");

        postDao.addPost(post);

        post = new Post();
        post.setPostId(2);
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("CodeJoker");
        post.setPost("What is a programmer's favorite hangout place? Foo Bar");

        postDao.addPost(post);

        List<Post> posts = postDao.getAllPosts();
        assertEquals(2, posts.size());
    }

    // tests getPostsByPosterName()
    @Test
    public void getPostsByPosterName() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("JavaHumorist");
        post.setPost("What is the object-oriented way to become wealthy? Inheritance");

        postDao.addPost(post);

        post = new Post();
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("JavaHumorist");
        post.setPost("What do computers and air conditioners have in common? They both become useless when you open Windows.");

        postDao.addPost(post);

        post = new Post();
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("CodeJoker");
        post.setPost("What is a programmer's favorite hangout place? Foo Bar");

        postDao.addPost(post);

        List<Post> postsByCodeJoker = postDao.getPostsByPosterName("JavaHumorist");
        assertEquals(2, postsByCodeJoker.size());

        List<Post> postsByJavaHumorist = postDao.getPostsByPosterName("CodeJoker");
        assertEquals(1, postsByJavaHumorist.size());

        List<Post> postsByDuckDuckCode= postDao.getPostsByPosterName("DuckDuckCode");
        assertEquals(0, postsByDuckDuckCode.size());
    }

}
