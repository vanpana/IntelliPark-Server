package com.cyberschnitzel.smartspark.domain.Entities;

public class Notification {
    private int id;
    private String notification;
    private String toWho;
    private String fromWhom;

    public Notification(int id, String notification, String toWho, String fromWhom) {
        this.id = id;
        this.notification = notification;
        this.toWho = toWho;
        this.fromWhom = fromWhom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notification='" + notification + '\'' +
                ", toWho='" + toWho + '\'' +
                ", fromWhom='" + fromWhom + '\'' +
                '}';
    }
}
