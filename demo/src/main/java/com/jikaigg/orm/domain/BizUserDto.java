package com.jikaigg.orm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizUserDto {
    private int id;
    private String username;
    private String phonenum;
    private String address;
}
