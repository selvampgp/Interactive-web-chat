package com.ivannyrkov.chat.controllers;

import com.ivannyrkov.chat.models.Color;
import com.ivannyrkov.chat.models.Message;
import com.ivannyrkov.chat.models.User;
import com.ivannyrkov.chat.models.datatransfer.MessageRequest;
import com.ivannyrkov.chat.models.datatransfer.MessageResponse;
import com.ivannyrkov.chat.models.datatransfer.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ivan Nyrkov
 */
@Controller
public class MainController {

    private List<User> users = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String login(Model model) {
        model.addAttribute("userNick", "");
        model.addAttribute("colors", Color.values());
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestParam("nickName") String nickname,
                        @RequestParam("colorSelected") Color colorUser,
                        Model model) {
        User newUser = new User(nickname, colorUser);
        users.add(newUser);
        model.addAttribute("userID", newUser.getUserId());
        model.addAttribute("userNick", newUser.getNickName());
        return "messages";
    }

    @RequestMapping(value = "/messages/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void postMessage(@RequestBody MessageRequest messagePost) {
        Optional<User> user = users.stream()
                .filter(u -> u.getUserId() == messagePost.getUserId())
                .findFirst();
        if (user.isPresent()) {
            messages.add(new Message(messagePost.getText(), new Date(), user.get()));
        }
    }


    @RequestMapping(value = "/messages/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<MessageResponse> getMessages(@PathVariable("userId") long userId) {
        return messages.stream()
                .map(m -> new MessageResponse(m.getText(), m.getUser().getNickName(),
                        m.getUser().getUserColor().name(), m.getPostedAt()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/messages/users/{userId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void logout(@PathVariable("userId") long userId) {
        Optional<User> user = users.stream()
                .filter(u -> u.getUserId() == userId)
                .findFirst();
        if (user.isPresent()) {
            users.remove(user.get());
        }
    }

    @RequestMapping(value = "/messages/users/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserResponse> getUsers(@PathVariable("userId") long userId) {
        if (userId != 0) {
            return users.stream()
                    .filter(u -> u.getUserId() != userId)
                    .map(u -> new UserResponse(u.getUserId(), u.getNickName(), u.getUserColor().toString()))
                    .collect(Collectors.toList());
        }
        return null;
    }

}
