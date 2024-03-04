package com.example.demo3.chat;

import com.example.demo3.SessionConst;
import com.example.demo3.user.User;
import com.example.demo3.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/subscribes")
public class SubscribeController {

    private final UserRepository userRepository;

    @GetMapping("/add")
    public String addSubscribeForm(@SessionAttribute(name= SessionConst.LOGIN_USER,required = false) User loginUser, Model model){

        if(loginUser ==null){
            return "home";
        }

        model.addAttribute("user",loginUser);
        model.addAttribute("title",new Subscribe());
        return "subscribes/addSubscribeForm";
    }

    @PostMapping("/add")
    public String save(@SessionAttribute(name= SessionConst.LOGIN_USER,required = false) User loginUser, @ModelAttribute Subscribe subscribe){
        User user = userRepository.findByLoginId(loginUser.getLoginId()).filter(u->u.getLoginId().equals(loginUser.getLoginId())).orElse(null);
        assert user != null;
        if(user.getSubscribeList().contains(subscribe.getName()))
            return "redirect:/";

        user.addSubscribe(subscribe.getName());

        return "redirect:/";
    }
}
