package com.example.consumer.dto;

import java.sql.Date;

public class MessageDto {

    private Integer id;
    private String message;
    private Date date;
    private Integer key;

    public MessageDto() {
    }

    public MessageDto(Integer id, String message, Date date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", key=" + key +
                '}';
    }
}
