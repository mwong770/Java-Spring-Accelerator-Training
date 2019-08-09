package com.trilogyed.post.dao;

import com.trilogyed.post.model.Post;

import java.util.List;

public interface PostDao {

    // standard CRUD

    Post addPost(Post comment);

    Post getPost(int id);

    void updatePost(Post post);

    void deletePost(int id);

    // additional methods

    List<Post> getPostsByPosterName(String posterName);

    // needed for testing

    List<Post> getAllPosts();

}
