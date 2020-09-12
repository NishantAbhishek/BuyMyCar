package com.example.buymycar.Model;

public class Cars {
    private String postimage;
    private String description;
    private String price;

    public Cars() {

    }

    public Cars(String postimage, String description, String price) {
        this.postimage = postimage;
        this.description = description;
        this.price = price;
    }




    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
