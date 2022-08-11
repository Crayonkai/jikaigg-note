package com.jikaigg.note.base;

public enum StatusEnum {
    RED("1","JIKAI"),YELLOW("2","MENGYU");
    private String id;
    private String name;
    private StatusEnum(String id,String name){
        this.id = id;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
}
