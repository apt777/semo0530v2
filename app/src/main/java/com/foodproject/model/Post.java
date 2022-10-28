package com.foodproject.model;

public class Post {

    private int postId;
    private String postTitle;
    private String comments;

    public Post(int postId, String postTitle, String comments) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.comments = comments;
    }

    public int getpostId() {
        return postId;
    }

    public String getpostTitle() {
        return postTitle;
    }

    public String getcomments() {
        return comments;
    }

}
