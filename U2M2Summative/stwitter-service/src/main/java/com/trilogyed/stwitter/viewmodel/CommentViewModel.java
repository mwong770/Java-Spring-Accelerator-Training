package com.trilogyed.stwitter.viewmodel;

import java.time.LocalDate;
import java.util.Objects;

// Returns comment data without post id
// Used to return comments with post data
// Distracting to have a list of comments each with a post id
// when the post id is already included in the post data
public class CommentViewModel {

    private int commentId;

    private LocalDate createDate;

    private String commenterName;

    private String comment;

    // constructors

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentViewModel that = (CommentViewModel) o;
        return getCommentId() == that.getCommentId() &&
                getCreateDate().equals(that.getCreateDate()) &&
                getCommenterName().equals(that.getCommenterName()) &&
                getComment().equals(that.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentId(), getCreateDate(), getCommenterName(), getComment());
    }

    @Override
    public String toString() {
        return "CommentViewModel{" +
                "commentId=" + commentId +
                ", createDate=" + createDate +
                ", commenterName='" + commenterName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

}
