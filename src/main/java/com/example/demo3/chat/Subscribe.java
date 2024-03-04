package com.example.demo3.chat;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class Subscribe {

    public Subscribe(){

    }

    public Subscribe(String name) {
        this.name = name;
        this.id= UUID.randomUUID().toString();
    }

    public Subscribe(String name, String id){
        this.name=name;
        this.id=id;
    }

    @NotEmpty
    private String name;
    @NotEmpty
    private String id;
}
