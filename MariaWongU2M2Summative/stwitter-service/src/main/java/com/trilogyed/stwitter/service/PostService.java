package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.util.feign.PostClient;
import com.trilogyed.stwitter.util.messages.Comment;
import com.trilogyed.stwitter.util.messages.Post;
import com.trilogyed.stwitter.viewmodel.CommentViewModel;
import com.trilogyed.stwitter.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {

    @Autowired
    private PostClient postClient;

    @Autowired
    private CommentService commentService;

    public PostService() {
    }

    public PostService(PostClient postClient, CommentService commentService) {
        this.postClient = postClient;
        this.commentService = commentService;
    }

    public PostViewModel getPost(int id) {

        Post post = postClient.getPost(id);

        if (post == null) {
            return null;
        }

        return buildPostViewModel(post);
    }

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

    // Build View Models

    private PostViewModel buildPostViewModel(Post post) {

        PostViewModel postViewModel = new PostViewModel();
        postViewModel.setPostId(post.getPostId());
        postViewModel.setPostDate(post.getPostDate());
        postViewModel.setPosterName(post.getPosterName());
        postViewModel.setPost(post.getPost());

        List<Comment> comments = commentService.getCommentsByPostId(post.getPostId());

        List<CommentViewModel> commentViewModels = new ArrayList<>();

        // removes post id from comments data returned to the user
        for (Comment c: comments) {
            commentViewModels.add(buildCommentViewModel(c));
        }

        postViewModel.setComments(commentViewModels);

        return postViewModel;
    }

    // Returns comment data without post id
    // Distracting to have a list of comments each with a post id
    // when the post id is already included in the post data
    private CommentViewModel buildCommentViewModel(Comment comment) {
        CommentViewModel commentViewModel = new CommentViewModel();
        commentViewModel.setCommentId(comment.getCommentId());
        commentViewModel.setCreateDate(comment.getCreateDate());
        commentViewModel.setCommenterName(comment.getCommenterName());
        commentViewModel.setComment(comment.getComment());

        return commentViewModel;
    }

}
