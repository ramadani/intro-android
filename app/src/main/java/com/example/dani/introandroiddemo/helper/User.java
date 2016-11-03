package com.example.dani.introandroiddemo.helper;

import java.util.ArrayList;

public class User {
    public String name;
    public String hometown;

    public User(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        users.add(new User("Randa", "Sangatta"));
        users.add(new User("Artha", "Jogja"));
        users.add(new User("Adi", "Pekanbaru"));
        users.add(new User("Eko", "Pekanbaru"));
        users.add(new User("Alyas", "Balikpapan"));

        return users;
    }
}
