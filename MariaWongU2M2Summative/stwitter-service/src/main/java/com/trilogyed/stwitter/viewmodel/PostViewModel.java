package com.trilogyed.stwitter.viewmodel;

import com.trilogyed.stwitter.util.messages.Comment;
import com.trilogyed.stwitter.util.messages.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PostViewModel extends Post {

    private List<Comment> comments;

    // constructors

    public PostViewModel() {
    }

    public PostViewModel(int postId, LocalDate postDate, String posterName, String post, List<Comment> comments) {
        super(postId, postDate, posterName, post);
        this.comments = comments;
    }

    // getters and setters

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PostViewModel that = (PostViewModel) o;
        return Objects.equals(getComments(), that.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getComments());
    }

    @Override
    public String toString() {
        return "PostViewModel{" +
                "postId=" + postId +
                ", postDate=" + postDate +
                ", posterName='" + posterName + '\'' +
                ", post='" + post + '\'' +
                ", comments=" + comments +
                '}';
    }
}
