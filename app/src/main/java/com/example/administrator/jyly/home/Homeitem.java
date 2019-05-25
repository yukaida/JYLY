package com.example.administrator.jyly.home;

public class Homeitem {
    int icon;
    String name;

    public Homeitem(int icon,String name) {
        this.icon=icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
