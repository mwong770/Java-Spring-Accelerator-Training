package com.trilogyed.comment.dao;

import com.trilogyed.comment.model.Comment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommentDaoJdbcTemplateImpl implements CommentDao {

    private JdbcTemplate jdbcTemplate;

    // prepared statements

    private static final String INSERT_COMMENT_SQL =
            "insert into comment (post_id, create_date, commenter_name, comment) values (?, ?, ?, ?)";

    private static final String SELECT_COMMENT_SQL =
            "select * from comment where comment_id = ?";

    private static final String UPDATE_COMMENT_SQL =
            "update comment set post_id = ?, create_date = ?, commenter_name = ?, comment = ? where comment_id = ?";

    private static final String DELETE_COMMENT_SQL =
            "delete from comment where comment_id = ?";

    private static final String SELECT_ALL_COMMENTS_SQL =
            "select * from comment";

    private static final String SELECT_ALL_COMMENTS_BY_POST_ID_SQL =
            "select * from comment where post_id = ?";

    private static final String DELETE_COMMENT_BY_POST_ID_SQL =
            "delete from comment where post_id = ?";

    // constructor


    public CommentDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // implement methods

    @Override
    @Transactional
    public Comment addComment(Comment comment) {
        jdbcTemplate.update(
                INSERT_COMMENT_SQL,
                comment.getPostId(),
                comment.getCreateDate(),
                comment.getCommenterName(),
                comment.getComment()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        comment.setCommentId(id);

        return comment;
    }

    @Override
    public Comment getComment(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_COMMENT_SQL, this::mapRowToComment, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this id, return null
            return null;
        }
    }


    @Override
    @Transactional
    public void updateComment(Comment comment) {

        Comment commentInDB = getComment(comment.getCommentId());
        if (commentInDB == null) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }

        jdbcTemplate.update(
                UPDATE_COMMENT_SQL,
                comment.getPostId(),
                comment.getCreateDate(),
                comment.getCommenterName(),
                comment.getComment(),
                comment.getCommentId()
        );

    }

    @Override
    @Transactional
    public void deleteComment(int id) {

        Comment commentInDB = getComment(id);
        if (commentInDB == null) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }

        jdbcTemplate.update(DELETE_COMMENT_SQL, id);

    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        return jdbcTemplate.query(SELECT_ALL_COMMENTS_BY_POST_ID_SQL, this::mapRowToComment, postId);
    }

    @Override
    public List<Comment> getAllComments() {
        return jdbcTemplate.query(SELECT_ALL_COMMENTS_SQL, this::mapRowToComment);
    }

    @Override
    @Transactional
    public void deleteCommentByPostId(int postId) {

//        Comment commentInDB = getComment(postId); // *** would need to use getCommentByPostId
//        if (commentInDB == null) {
//            throw new IllegalArgumentException("The id provided does not exist.");
//        }

        jdbcTemplate.update(DELETE_COMMENT_BY_POST_ID_SQL, postId);

    }

    // Row Mapper

    private Comment mapRowToComment(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("comment_id"));
        comment.setCreateDate(rs.getDate("create_date").toLocalDate());
        comment.setPostId(rs.getInt("post_id"));
        comment.setCommenterName(rs.getString("commenter_name"));
        comment.setComment(rs.getString("comment"));

        return comment;
    }

}
