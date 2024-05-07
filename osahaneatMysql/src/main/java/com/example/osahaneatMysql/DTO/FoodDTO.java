package com.example.osahaneatMysql.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
public class FoodDTO {
    private int id;
    private String title;
    private String image;
    private boolean isFreeship;
    private String timeShip;
    private double price;
    private int cateId;
    private String desc;
    public FoodDTO() {
    }
}
