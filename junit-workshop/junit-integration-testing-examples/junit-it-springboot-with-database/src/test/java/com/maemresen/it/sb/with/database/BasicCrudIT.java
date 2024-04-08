package com.maemresen.it.sb.with.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BasicCrudIT {

    @Autowired  // Injects the required Spring bean, in this case, our service for managing persons
    private PersonService personService;

    @Test
    void testQueryingNonexistentPerson() {
        // Try to retrieve a person that surely doesn't exist
        Optional<Person> foundPerson = personService.findByUsername("nonexistentperson");

        // We expect no person to be found in such a case
        assertTrue(foundPerson.isEmpty(), "Querying for a non-existent person should return null");
    }
}
