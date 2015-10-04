package com.ivannyrkov.chat.models.datatransfer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Ivan Nyrkov
 */
public class MessageResponse {

    private String textResp;
    private String postedBy;
    private String color;
    private String time;

    public MessageResponse() {
    }

    public MessageResponse(String textResp, String postedBy, String color, Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        this.time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        this.textResp = textResp;
        this.postedBy = postedBy;
        this.color = color;
    }

    public String getTextResp() {
        return textResp;
    }

    public void setTextResp(String textResp) {
        this.textResp = textResp;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
