package com.maemresen.junit.ut.mockito.service.repository;

import com.maemresen.junit.ut.mockito.service.domain.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByName(String name);
}
