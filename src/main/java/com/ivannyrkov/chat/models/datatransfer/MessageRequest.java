package com.ivannyrkov.chat.models.datatransfer;

/**
 * @author Ivan Nyrkov
 */
public class MessageRequest {

    private String text;
    private long userId;

    public MessageRequest() {
        super();
    }

    public MessageRequest(String text, long userId) {
        this.text = text;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
