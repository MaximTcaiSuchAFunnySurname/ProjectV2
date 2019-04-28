package com.example.maxim.protov40.util;

import java.io.Serializable;

public class Session implements Serializable {
    private static final Session INSTANCE = new Session();
    private User user;

    private Session(){

    }

    public static Session getINSTANCE() {
        return INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Session{" +
                "INSTANCE=" + INSTANCE +
                ", user='" + user + '\'' +
                '}';
    }
}
