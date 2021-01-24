package com.example.maxim.protov40.util;

public interface ILogin {
    public boolean login(String login, String password);
    public void writeNewUser(String login, String password);
}
