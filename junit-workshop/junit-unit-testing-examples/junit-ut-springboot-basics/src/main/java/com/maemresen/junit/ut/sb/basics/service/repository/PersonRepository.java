package com.maemresen.junit.ut.sb.basics.service.repository;


import com.maemresen.junit.ut.sb.basics.service.domain.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByName(String name);
}
