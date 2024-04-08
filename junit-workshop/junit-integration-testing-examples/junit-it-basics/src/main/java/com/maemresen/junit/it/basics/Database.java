package com.maemresen.junit.it.basics;

public interface Database {
    void store(User user);
    User findByUsername(String username);
}
