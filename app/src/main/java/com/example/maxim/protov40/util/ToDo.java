package com.example.maxim.protov40.util;

import android.icu.util.LocaleData;

import java.io.Serializable;

public class ToDo implements Serializable {
    private String name;
    private String disc;
    private String time;

    public ToDo(String name, String disc, String time) {
        this.name = name;
        this.disc = disc;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "name='" + name + '\'' +
                ", disc='" + disc + '\'' +
                ", time=" + time +
                '}';
    }
}
