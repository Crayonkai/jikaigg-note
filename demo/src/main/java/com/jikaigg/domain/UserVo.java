package com.jikaigg.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String username;

    public static void main(String[] args) {
        Integer num = 1<<30;
        System.out.println(num);
    }
}
