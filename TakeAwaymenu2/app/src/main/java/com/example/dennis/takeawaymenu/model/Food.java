package com.example.dennis.takeawaymenu.model;

public class Food {
    private String name;
    private String price;
    private int imageId;


    public Food(String name,String price, int image){
        this.name=name;
        this.price=price;
        this.imageId=image;
    }
    public Food(String name,String desc){
        this.name=name;
        this.price=desc;
    }
    //getting
    public String getName() {
        return this.name;
    }

    public String getPrice() {
        return this.price;
    }

    public int getImageId() {
        return this.imageId;
    }

    //setting

    public void setPrice(String price) {
        this.price = this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
