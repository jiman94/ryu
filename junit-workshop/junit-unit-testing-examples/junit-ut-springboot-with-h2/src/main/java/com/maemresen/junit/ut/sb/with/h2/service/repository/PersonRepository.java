package com.maemresen.junit.ut.sb.with.h2.service.repository;

import com.maemresen.junit.ut.sb.with.h2.service.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
}
