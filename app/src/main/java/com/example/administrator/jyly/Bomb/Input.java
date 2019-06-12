package com.example.administrator.jyly.Bomb;

import android.media.Image;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Input extends BmobObject {
    String inputmes;
    private BmobFile image;

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public String getInputmes() {
        return inputmes;
    }

    public void setInputmes(String inputmes) {
        this.inputmes = inputmes;
    }
}
