package com.trilogyed.post.dao;

import com.trilogyed.post.exception.NotFoundException;
import com.trilogyed.post.model.Post;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostDaoJdbcTemplateImpl implements PostDao {

    private JdbcTemplate jdbcTemplate;

    // prepared statements

    private static final String INSERT_POST_SQL =
            "insert into post (post_date, poster_name, post) values (?, ?, ?)";

    private static final String SELECT_POST_SQL =
            "select * from post where post_id = ?";

    private static final String UPDATE_POST_SQL =
            "update post set post_date = ?, poster_name = ?, post = ? where post_id = ?";

    private static final String DELETE_POST_SQL =
            "delete from post where post_id = ?";

    private static final String SELECT_ALL_POSTS_SQL =
            "select * from post";

    private static final String SELECT_ALL_POSTS_BY_POSTER_SQL =
            "select * from post where poster_name = ?";

    // constructor

    public PostDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // implement methods

    @Override
    @Transactional
    public Post addPost(Post post) {
        jdbcTemplate.update(
                INSERT_POST_SQL,
                post.getPostDate(),
                post.getPosterName(),
                post.getPost()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        post.setPostId(id);

        return post;
    }

    @Override
    public Post getPost(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_POST_SQL, this::mapRowToPost, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this id, return null
            return null;
        }
    }

    @Override
    @Transactional
    public void updatePost(Post post) {

        // checks for id first so user knows if anything was updated
        // user could have unknowingly entered the wrong id
        Post postInDB = getPost(post.getPostId());
        if (postInDB == null) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }

        jdbcTemplate.update(
                UPDATE_POST_SQL,
                post.getPostDate(),
                post.getPosterName(),
                post.getPost(),
                post.getPostId()
        );
    }

    @Override
    @Transactional
    public void deletePost(int id) {

        // checks for id first so user knows if anything was deleted
        // user could have unknowingly entered the wrong id
        Post postInDB = getPost(id);
        if (postInDB == null) {
            throw new NotFoundException("The id provided does not exist.");
        }

        jdbcTemplate.update(DELETE_POST_SQL, id);
    }

    @Override
    public List<Post> getPostsByPosterName(String posterName) {
        return jdbcTemplate.query(SELECT_ALL_POSTS_BY_POSTER_SQL, this::mapRowToPost, posterName);
    }

    @Override
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SELECT_ALL_POSTS_SQL, this::mapRowToPost);
    }

    // Row Mapper

    private Post mapRowToPost(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setPostId(rs.getInt("post_id"));
        post.setPostDate(rs.getDate("post_date").toLocalDate());
        post.setPosterName(rs.getString("poster_name"));
        post.setPost(rs.getString("post"));

        return post;
    }

}
