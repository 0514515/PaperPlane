package com.example.demo3.chat;

import com.example.demo3.SessionConst;
import com.example.demo3.user.User;
import com.example.demo3.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserRepository userRepository;

    @GetMapping("/chat/{subscribe}")
    public String enterChat(@SessionAttribute(name= SessionConst.LOGIN_USER,required = false) User loginUser, @PathVariable String subscribe,Model model) {

        if(loginUser==null){
            return "home";
        }

        String subscribeId=null;

        for(Subscribe subscribe1 : loginUser.getSubscribeList()){
            if(subscribe1.getId().equals(subscribe)){
                subscribeId=subscribe1.getId();
            }
        }

        model.addAttribute("user",loginUser);
        model.addAttribute("subscribeId",subscribeId);
        System.out.println(loginUser.getName());
        return "chat";
    }

    /*@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message messaging(Message message) throws Exception {
        Thread.sleep(100); // simulated delay
        return new Message(message.getFrom() + " : " + message.getMessage());
    }*/

    @MessageMapping("/hello")
    public void messaging(Message message) throws Exception {
        Thread.sleep(100); // simulated delay
        if(message.getType().equals("message") && !message.getMessage().equals("")){
            System.out.println("ok");
            simpMessagingTemplate.convertAndSend("/topic/"+message.getSubscribeId(),new Message(message.getFrom() + " : " + message.getMessage()));
        }

        else if(message.getType().equals("invite")){
            invite(message);
        }

    }

    public void invite(Message message) throws Exception{
        Thread.sleep(100); // simulated delay
        User toUser = userRepository.findByName(message.getMessage()).
                filter(u->u.getName().equals(message.getMessage())).
                orElse(null);

        User fromUser = userRepository.findByName(message.getFrom()).
                filter(u->u.getName().equals(message.getFrom())).
                orElse(null);

        String subscribeName=null;

        for(Subscribe subscribe1 : fromUser.getSubscribeList()){
            if(subscribe1.getId().equals(message.getSubscribeId())){
                subscribeName=subscribe1.getName();
            }
        }

        if(!toUser.getSubscribeList().contains(new Subscribe(subscribeName, message.getSubscribeId()))) {
            toUser.invite(subscribeName, message.getSubscribeId());
            simpMessagingTemplate.convertAndSend("/topic/"+message.getSubscribeId(),new Message("***** "+toUser.getName() + "님 초대 *****"));
        }

        else if(toUser.getSubscribeList().contains(new Subscribe(subscribeName, message.getSubscribeId()))){
            simpMessagingTemplate.convertAndSend("/topic/"+message.getSubscribeId(),new Message("초대 실패 : " + toUser.getName() + "님은 이미 있습니다."));
        }

    }
}
