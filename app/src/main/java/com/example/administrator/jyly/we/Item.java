package com.example.administrator.jyly.we;

public class Item {
    private int pic;
    private String name;

    public Item(int pic, String name) {
        this.name = name;
        this.pic = pic;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
