package com.example.maxim.protov40.util;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;

@IgnoreExtraProperties
public class User implements Serializable {
    private String login;
    private String password;
    private List<Folder> list;

    public User(){

    }

    public User(String login, String password, List<Folder> list) {
        this.login = login;
        this.password = password;
        this.list = list;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Folder> getList() {
        return list;
    }

    public void setList(List<Folder> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", list=" + list +
                '}';
    }
}
