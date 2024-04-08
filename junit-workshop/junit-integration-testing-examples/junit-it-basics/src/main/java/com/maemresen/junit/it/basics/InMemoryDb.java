package com.maemresen.junit.it.basics;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDb implements Database {

    private List<User> USER_DATABASE = new ArrayList<>();

    @Override
    public void store(User user) {
        USER_DATABASE.add(user);
    }

    @Override
    public User findByUsername(String username) {
        return USER_DATABASE.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
}
