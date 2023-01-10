package com.enigma.model.request;

import lombok.Data;

@Data
public class MenuRequest {
    private String menuName;
    private Integer price;
    private String categoryId;
}
