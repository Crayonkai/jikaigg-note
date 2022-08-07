package com.jikaigg.service;

public class CoverUtil {
    public static void main(String[] args) {
        String str = "column1:column11,column2:column22,column3:column33,column4:column44,column5:column55";
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : str.split(",")) {
            String[] column = s.split(":");
            stringBuilder.append(column[1]).append(":").append(column[0]).append(",");
        }
        System.out.println(stringBuilder.substring(0,stringBuilder.length()-1));
    }
}
