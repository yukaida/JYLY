package com.example.administrator.jyly.Bomb;

import cn.bmob.v3.BmobObject;

public class Order extends BmobObject {

    private int OrderNumber;
    private String name;



    public int getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
