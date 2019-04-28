package com.example.maxim.protov40.util;

import java.io.Serializable;

public class ToDo implements Serializable {
    private String name;
    private String data;
    private String text;

    public ToDo(String name, String text, String data) {
        this.name = name;
        this.text = text;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
