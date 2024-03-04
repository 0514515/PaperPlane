package com.example.demo3.user;

import com.example.demo3.chat.Subscribe;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private List<Subscribe> subscribeList = new ArrayList<Subscribe>();
    public void addSubscribe(String name) {subscribeList.add(new Subscribe(name));}

    public void invite(String name,String subscribeId){subscribeList.add(new Subscribe(name,subscribeId));}

    public List<Subscribe> getSubscribeList() {return subscribeList;}
}
