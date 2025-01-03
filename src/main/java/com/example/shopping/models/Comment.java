package com.example.shopping.models;

public class Comment {
    private String user;
    private String text;
    private int rating; // Rating for the comment, between 1-5

    // Constructor, getters, and setters

    public Comment(String user, String text, int rating) {
        this.user = user;
        this.text = text;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}