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

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Ivan Nyrkov
 */
@Controller
public class MainController {

    private Map<Long, User> users = new ConcurrentHashMap<>();
    private List<Message> messages = new ArrayList<>();

    /**
     * To login page
     * GET /
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String home(Model model) {
        model.addAttribute("colors", Color.values());
        return "login";
    }

    /**
     * Login
     * POST /
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestParam("nickName") String nickname,
                        @RequestParam("colorSelected") Color colorUser,
                        Model model) {
        User newUser = new User(nickname, colorUser);
        users.put(newUser.getUserId(), newUser);
        model.addAttribute("userID", newUser.getUserId());
        model.addAttribute("userNick", newUser.getNickName());
        return "messages";
    }

    /**
     * Post new message
     * POST /messages/
     */
    @RequestMapping(value = "/messages/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void postMessage(@RequestBody MessageRequest messagePost) {
        User user = users.get(messagePost.getUserId());
        if (user != null) {
            messages.add(new Message(messagePost.getText(), new Date(), user));
        }
    }


    /**
     * Get all messages
     * GET /messages/
     */
    @RequestMapping(value = "/messages/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<MessageResponse> getMessages() {
        return messages.stream()
                .map(m -> new MessageResponse(
                        m.getText(),
                        m.getUser().getNickName(),
                        m.getUser().getUserColor().name(),
                        m.getPostedAt()))
                .collect(Collectors.toList());
    }

    /**
     * Logout
     * POST /messages/users/{userId}
     */
    @RequestMapping(value = "/messages/users/{userId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void logout(@PathVariable("userId") long userId) {
        users.remove(userId);
    }

    /**
     * Get all users (except of current)
     * GET /messages/users/{userId}
     */
    @RequestMapping(value = "/messages/users/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserResponse> getUsers(@PathVariable("userId") long userId) {
        if (userId != 0) {
            return users.values().stream()
                    .filter(user -> user.getUserId() != userId)
                    .map(user -> new UserResponse(user.getUserId(), user.getNickName(), user.getUserColor().toString()))
                    .collect(Collectors.toList());
        }
        return null;
    }

}
