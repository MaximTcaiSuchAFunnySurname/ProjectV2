package com.example.maxim.protov40.util;

import java.io.Serializable;
import java.util.List;

public class Folder implements Serializable {
    private String name;
    private List<ToDo> todos;

    public Folder(String name, List<ToDo> todos) {
        this.name = name;
        this.todos = todos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(List<ToDo> todos) {
        this.todos = todos;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                ", todos=" + todos +
                '}';
    }
}
