package com.example.demo3.login;

import com.example.demo3.user.User;
import com.example.demo3.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public User login(String loginId, String password){
        return userRepository.findByLoginId(loginId).
                filter(u->u.getPassword().equals(password)).
                orElse(null);
    }


}
