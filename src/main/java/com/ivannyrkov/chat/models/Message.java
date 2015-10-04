package com.ivannyrkov.chat.models;

import java.util.Date;

/**
 * @author Ivan Nyrkov
 */
public class Message {

    private String text;
    private Date postedAt;
    private User user;

    public Message() {
    }

    public Message(String text, Date postedAt, User user) {
        this.text = text;
        this.postedAt = postedAt;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!text.equals(message.text)) return false;
        if (!postedAt.equals(message.postedAt)) return false;
        return user.equals(message.user);

    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + postedAt.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}
