package com.example.demo3;

import com.example.demo3.user.User;
import com.example.demo3.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestData {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        User user = new User();
        user.setLoginId("user1");
        user.setPassword("1234");
        user.setName("User1");
        userRepository.save(user);
        user.addSubscribe("subscribe1");
        user.addSubscribe("subscribe2");
        user.addSubscribe("subscribe3");


        User user1 = new User();
        user1.setLoginId("user2");
        user1.setPassword("1234");
        user1.setName("User2");
        userRepository.save(user1);
        user1.addSubscribe("subscribe1");
        user1.addSubscribe("subscribe2");
        user1.addSubscribe("subscribe3");
    }
}
