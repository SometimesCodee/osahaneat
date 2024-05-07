package com.example.osahaneatMysql.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class RestaurantDTO {
    private int id;
    private String title;
    private String subtitle;
    private String description;
    private String image;
    private boolean isFreeship;
    private String address;
    private Date openDate;
    private double rating;
    List<CategoryDTO> category;

    public RestaurantDTO() {
    }
}
