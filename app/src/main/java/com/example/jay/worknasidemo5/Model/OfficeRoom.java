package com.example.jay.worknasidemo5.Model;

import com.example.jay.worknasidemo5.R;

/**
 * Created by Jay on 11/21/2017.
 */

public class OfficeRoom {

    private int id;
    private String roomImage;
    private String roomTitle;
    private String roomType;
    private double price;
    private String duration;


    public OfficeRoom(int id, String image, String title, String type, double price, String duration){
        this.id = id;
        this.roomImage = image;
        this.roomTitle = title;
        this.roomType = type;
        this.price = price;
        this.duration = duration;
    }



    public int getId() {
        return id;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }
}
