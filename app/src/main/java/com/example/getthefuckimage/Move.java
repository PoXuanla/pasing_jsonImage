package com.example.getthefuckimage;

public class Move {
    private String thumbnailUrl="";

    public Move(){
    }
    public Move(String thumbnailUrl){
        this.thumbnailUrl = thumbnailUrl;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

}

