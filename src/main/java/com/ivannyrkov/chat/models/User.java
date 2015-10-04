package com.ivannyrkov.chat.models;

import java.util.Random;

/**
 * @author Ivan Nyrkov
 */
public class User {

    private static final Random random = new Random();

    private long userId;
    private String nickName;
    private Color userColor;

    public User(String nickName, Color userColor) {
        this.userId = random.nextLong();
        this.nickName = nickName;
        this.userColor = userColor;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Color getUserColor() {
        return userColor;
    }

    public void setUserColor(Color userColor) {
        this.userColor = userColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (!nickName.equals(user.nickName)) return false;
        return userColor == user.userColor;

    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + nickName.hashCode();
        result = 31 * result + userColor.hashCode();
        return result;
    }
}
