package com.ivannyrkov.chat.models.datatransfer;

/**
 * @author Ivan Nyrkov
 */
public class UserResponse {
    private long idUser;
    private String nickName;
    private String color;

    public UserResponse() {
    }

    public UserResponse(long userId, String nickName, String color) {
        this.idUser = userId;
        this.nickName = nickName;
        this.color = color;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long userId) {
        this.idUser = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
