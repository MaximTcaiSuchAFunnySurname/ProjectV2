package com.example.maxim.protov40.util;

import android.widget.TextView;

import java.io.Serializable;

public class Session implements Serializable {
    private static final Session INSTANCE = new Session();
    private User user;
    private int folderPosition;
    private int todoPosition;
    private TextView deadline;

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

    public int getFolderPosition() {
        return folderPosition;
    }

    public void setFolderPosition(int folderPosition) {
        this.folderPosition = folderPosition;
    }

    public int getTodoPosition() {
        return todoPosition;
    }

    public void setTodoPosition(int todoPosition) {
        this.todoPosition = todoPosition;
    }

    public TextView getDeadline() {
        return deadline;
    }

    public void setDeadline(TextView deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Session{" +
                "INSTANCE=" + INSTANCE +
                ", user='" + user + '\'' +
                '}';
    }
}
