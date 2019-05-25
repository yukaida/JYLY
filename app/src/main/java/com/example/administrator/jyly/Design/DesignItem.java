package com.example.administrator.jyly.Design;

public class DesignItem {
    int Image;
    String mes;

    public DesignItem(int picture,String mess) {
        Image = picture;
        mes = mess;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
