package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.util.feign.PostClient;
import com.trilogyed.stwitter.util.messages.Comment;
import com.trilogyed.stwitter.util.messages.Post;
import com.trilogyed.stwitter.viewmodel.CommentViewModel;
import com.trilogyed.stwitter.viewmodel.PostViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostServiceTest {

    PostService postService;

    private PostClient postClient;

    private CommentService commentService;

    @Before
    public void setUp() {

        // configure mock objects
        setUpPostClientMock();

        setUpCommentServiceMock();

        // Passes mock objects
        postService = new PostService(postClient, commentService);

    }

    // tests addPost () and getPost()
    @Test
    public void addGetPost() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("JavaHumorist");
        post.setPost("What do computers and air conditioners have in common? They both become useless when you open Windows.");

        PostViewModel postVM = postService.addPost(post);

        PostViewModel postVM2 = postService.getPost(postVM.getPostId());

        assertEquals(postVM, postVM2);

    }

    // tests getPostsByPosterName()
    @Test
    public void getPostsByPosterName() {

        PostViewModel javaHumoristVM = new PostViewModel();
        javaHumoristVM.setPostId(1);
        javaHumoristVM.setPostDate(LocalDate.of(2019, 8, 01));
        javaHumoristVM.setPosterName("JavaHumorist");
        javaHumoristVM.setPost("What do computers and air conditioners have in common? They both become useless when you open Windows.");
        javaHumoristVM.setComments(new ArrayList<>());

        CommentViewModel commentVM = new CommentViewModel();
        commentVM.setCommentId(1);
        commentVM.setCreateDate(LocalDate.of(2019, 8, 15));
        commentVM.setCommenterName("CoderLife");
        commentVM.setComment("Stealing!!!");

        List<CommentViewModel> commentList = new ArrayList<>();
        commentList.add(commentVM);

        PostViewModel codeJokerVM = new PostViewModel();
        codeJokerVM.setPostId(2);
        codeJokerVM.setPostDate(LocalDate.of(2019, 8, 01));
        codeJokerVM.setPosterName("CodeJoker");
        codeJokerVM.setPost("What is a programmer's favorite hangout place? Foo Bar");
        codeJokerVM.setComments(commentList);

        List<PostViewModel> javaHumoristList = postService.getPostsByPosterName("JavaHumorist");

        assertEquals(2, javaHumoristList.size());
        assertEquals(javaHumoristVM, javaHumoristList.get(0));

        List<PostViewModel> codeJokerList = postService.getPostsByPosterName("CodeJoker");

        assertEquals(1, codeJokerList.size());
        assertEquals(codeJokerVM, codeJokerList.get(0));

        // tests when no posts are found with a matching poster name
        List<PostViewModel> duckDuckCodeList = postService.getPostsByPosterName("DuckDuckCode");

        assertEquals(0, duckDuckCodeList.size());

    }

    // tests deletePost()
    @Test
    public void deletePost() {
        PostViewModel postVM = postService.getPost(1);
        postService.deletePost(1);
        ArgumentCaptor<Integer> postCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(postClient).deletePost(postCaptor.capture());
        assertEquals(postVM.getPostId(), postCaptor.getValue().intValue());
    }

    // tests updatePost()
    @Test
    public void updatePost() {

        Post post = new Post();
        post.setPostId(1);
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("Javian");// instead of "JavaHumorist"
        post.setPost("What do computers and air conditioners have in common? They both become useless when you open Windows.");

        postService.updatePost(1, post);
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postClient).updatePost(any(Integer.class), postCaptor.capture());
        assertEquals(post.getPosterName(), postCaptor.getValue().getPosterName());

    }

    // tests if will return null if try to get post with non-existent id
    @Test
    public void getPostWithNonExistentId() {
        Post post = postService.getPost(500);
        assertNull(post);
    }

    // Create mocks

    public void setUpPostClientMock() {

        postClient = mock(PostClient.class);

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 8, 01));
        post.setPosterName("JavaHumorist");
        post.setPost("What do computers and air conditioners have in common? They both become useless when you open Windows.");

        Post post2 = new Post();
        post2.setPostId(1);
        post2.setPostDate(LocalDate.of(2019, 8, 01));
        post2.setPosterName("JavaHumorist");
        post2.setPost("What do computers and air conditioners have in common? They both become useless when you open Windows.");

        Post post3 = new Post();
        post3.setPostDate(LocalDate.of(2019, 8, 01));
        post3.setPosterName("CodeJoker");
        post3.setPost("What is a programmer's favorite hangout place? Foo Bar");

        Post post4 = new Post();
        post4.setPostId(2);
        post4.setPostDate(LocalDate.of(2019, 8, 01));
        post4.setPosterName("CodeJoker");
        post4.setPost("What is a programmer's favorite hangout place? Foo Bar");

        Post post5 = new Post();
        post5.setPostDate(LocalDate.of(2019, 8, 01));
        post5.setPosterName("JavaHumorist");
        post5.setPost("What is the object-oriented way to become wealthy? Inheritance");

        Post post6 = new Post();
        post6.setPostId(3);
        post6.setPostDate(LocalDate.of(2019, 8, 01));
        post6.setPosterName("JavaHumorist");
        post6.setPost("What is the object-oriented way to become wealthy? Inheritance");

        doReturn(post2).when(postClient).addPost(post);
        doReturn(post4).when(postClient).addPost(post3);
        doReturn(post6).when(postClient).addPost(post5);
        doReturn(post2).when(postClient).getPost(1);

        List<Post> codeJokerList = new ArrayList<>();
        codeJokerList.add(post4);

        List<Post> javaHumoristList = new ArrayList<>();
        javaHumoristList.add(post2);
        javaHumoristList.add(post6);

        List<Post> emptyList = new ArrayList<>();

        doReturn(codeJokerList).when(postClient).getPostsByPosterName("CodeJoker");
        doReturn(javaHumoristList).when(postClient).getPostsByPosterName("JavaHumorist");
        doReturn(emptyList).when(postClient).getPostsByPosterName("DuckDuckCode");

    }

    public void setUpCommentServiceMock() {

        commentService = mock(CommentService.class);

        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setPostId(2);
        comment.setCreateDate(LocalDate.of(2019, 8, 15));
        comment.setCommenterName("CoderLife");
        comment.setComment("Stealing!!!");

        List<Comment> commentsList = new ArrayList<>();

        commentsList.add(comment);

        doReturn(commentsList).when(commentService).getCommentsByPostId(2);

    }

}
