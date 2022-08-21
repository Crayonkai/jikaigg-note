package com.jikaigg.hotal.pojo;

import lombok.Data;

@Data
public class RequestParam {
    private String key;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String brand;
    private String city;
    private String starName;
    private String minPrice;
    private String maxPrice;
    private String location;

}
