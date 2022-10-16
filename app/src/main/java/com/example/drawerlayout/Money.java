package com.example.drawerlayout;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Money_Table")
public class Money implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String money;
    private String price;

    public Money( String money, String price) {

        this.money = money;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
