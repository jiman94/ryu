package com.maemresen.junit.it.basics;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserIT {

    private static Database database;
    private UserService userService;

    // Static initialization block executed once before any tests.
    // Used for setting up shared resources like a database connection.
    @BeforeAll
    static void setUp() {
        database = new InMemoryDb();
    }

    // Initialization method run before each test.
    // Used for setting up necessary objects and state.
    @BeforeEach
    void beforeEach() {
        userService = new UserService(database);
    }

    // Utility method to generate a unique random string.
    private String randomString() {
        return String.valueOf(System.currentTimeMillis());
    }

    // Utility method to generate a unique random username.
    private String randomUsername() {
        return "johndoe" + randomString();
    }

    // Utility method to generate a unique random password.
    private String randomPassword() {
        return "password" + randomString();
    }

    // Test the user registration functionality.
    // Checks if a newly registered user can be found in the database by their username.
    @Test
    void testUserRegistration() {
        String username = randomUsername();
        User newUser = new User(null, username, randomPassword());
        userService.register(newUser);
        User foundUser = database.findByUsername(username);
        assertNotNull(foundUser, "Newly registered user should be found in the database");
        assertEquals(username, foundUser.getUsername(), "Registered and found users should have the same username");
    }

    // Test the behavior when attempting to register a duplicate username.
    // Assumes that the system should throw an exception in such cases.
    @Test
    void testDuplicateUsernameRegistration() {
        String username = randomUsername();
        User firstUser = new User(null, username, randomPassword());
        userService.register(firstUser);

        User duplicateUser = new User(null, username, randomPassword());

        assertThrows(IllegalStateException.class, () -> userService.register(duplicateUser), "Registration with a duplicate username should throw an exception");
    }

    // Test the registration process when important user data (like username or password) is missing.
    // Assumes the system should throw an exception for incomplete data.
    @Test
    void testRegistrationWithMissingData() {
        User userMissingUsername = new User(null, null, "password123");
        User userMissingPassword = new User(null, "johndoe", null);

        assertThrows(IllegalArgumentException.class, () -> userService.register(userMissingUsername), "Registration with a missing username should throw an exception");
        assertThrows(IllegalArgumentException.class, () -> userService.register(userMissingPassword), "Registration with a missing password should throw an exception");
    }

    // Test querying for a user that doesn't exist in the database.
    // Assumes that the system should return null or an empty optional in such cases.
    @Test
    void testQueryingNonexistentUser() {
        User foundUser = database.findByUsername("nonexistentuser");
        assertNull(foundUser, "Querying for a non-existent user should return null");
    }
}
