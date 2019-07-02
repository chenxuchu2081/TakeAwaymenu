package com.example.dennis.takeawaymenu.model;

/**
 * Created by stcp on 27/6/2018.
 */

public class Comment {
    private String _id;
    private String datetime;
    private String comment;


    public  Comment(String _id,String datetime,String comment){
        this._id = _id;
        this.datetime = datetime;
        this.comment = comment;
    }
    @Override
    public String toString() {
        return " \n "  +datetime  +"  \n  " + " \n" + comment + "\n";
    }
}