package com.example.osahaneatMysql.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO {
    private String name;
    private List<FoodDTO> foods;

    public CategoryDTO() {
    }
}
