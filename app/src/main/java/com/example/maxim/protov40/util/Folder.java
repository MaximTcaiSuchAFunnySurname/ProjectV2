package com.example.maxim.protov40.util;

import java.io.Serializable;
import java.util.List;

public class Folder implements Serializable {
    private String name;
    private List<ToDo> list;

    public Folder(String name, List<ToDo> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ToDo> getList() {
        return list;
    }

    public void setList(List<ToDo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
