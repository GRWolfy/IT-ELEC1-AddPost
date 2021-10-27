package com.example.it_elec1_addpost;

import java.util.ArrayList;

public class Comments {
    private  String comment;
    private int position;
    private ArrayList<String> comments;

    public String getComments(int i) {
        return comments.get(i);
    }

    public void setComments(String comments) {
        this.comments.add(comments);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
