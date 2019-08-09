package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.util.feign.CommentClient;
import com.trilogyed.stwitter.util.feign.PostClient;
import com.trilogyed.stwitter.util.messages.Post;
import com.trilogyed.stwitter.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {

    @Autowired
    private CommentClient commentClient;

    @Autowired
    private PostClient postClient;

    @Autowired
    private CommentService commentService;

    public PostService() {
    }

    public PostViewModel getPost(int id) {

        Post post = postClient.getPost(id);

        return buildPostViewModel(post);
    }

//    public Post getPost(int id) {
//
//        return postClient.getPost(id);
//
//    }

    public List<PostViewModel> getPostsByPosterName(String posterName) {

        List<Post> posts = postClient.getPostsByPosterName(posterName);

        List<PostViewModel> postViewModels = new ArrayList<>();

        for (Post p: posts) {
            postViewModels.add(buildPostViewModel(p));
        }
        return postViewModels;
    }

    public PostViewModel addPost(Post post) {

        post = postClient.addPost(post);

        return buildPostViewModel(post);
    }

    public void updatePost(int id, Post post) {
        postClient.updatePost(id, post);
    }

    public void deletePost(@PathVariable int id) {
        commentService.deleteCommentByPostId(id);
        postClient.deletePost(id);
    }

    // Build View Model

    private PostViewModel buildPostViewModel(Post post) {
        PostViewModel postViewModel = new PostViewModel();
        postViewModel.setPostId(post.getPostId());
        postViewModel.setPostDate(post.getPostDate());
        postViewModel.setPosterName(post.getPosterName());
        postViewModel.setPost(post.getPost());

        postViewModel.setComments(commentService.getCommentsByPostId(post.getPostId()));

        return postViewModel;
    }

}
