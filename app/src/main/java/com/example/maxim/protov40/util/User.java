package com.example.maxim.protov40.util;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;

@IgnoreExtraProperties
public class User implements Serializable {
    private String id;
    private String login;
    private String password;
    private List<Folder> folders;

    public User(){

    }

    public User(String login, String password, List<Folder> folders) {
        this.login = login;
        this.password = password;
        this.folders = folders;
    }

    public User(String id, String login, String password, List<Folder> folders) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.folders = folders;
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

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", folders=" + folders +
                '}';
    }
}
