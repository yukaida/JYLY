package com.example.administrator.jyly.MyPublish;

public class PublishItem {
    int image;
    String message;

    public PublishItem(int image, String message) {
        this.image = image;
        this.message = message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
