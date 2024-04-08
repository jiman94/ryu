package com.maemresen.junit.it.basics;

public class UserService {

    private Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public void register(User user) {
        // Check if user or its essential attributes are null
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("User or essential attributes cannot be null.");
        }

        User existingUser = database.findByUsername(user.getUsername());

        if (existingUser != null) {
            throw new IllegalStateException("Username already exists.");
        }

        database.store(user);
    }
}
