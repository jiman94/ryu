package com.maemresen.junit.ut.sb.with.h2;

import com.maemresen.junit.ut.sb.with.h2.service.domain.Person;
import com.maemresen.junit.ut.sb.with.h2.service.exception.BusinessException;
import com.maemresen.junit.ut.sb.with.h2.service.impl.PersonServiceImpl;
import com.maemresen.junit.ut.sb.with.h2.service.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

// This annotation tells Spring Boot to look for a main configuration class
// and start a Spring application context. Here, we're also setting up properties
// for the H2 in-memory database, specifically for testing.
@SpringBootTest(properties = {
        // H2 database configuration
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        // Disabling ddl-auto since the repository will be mocked,
        // meaning we don't need to create tables in the H2 database.
        "spring.jpa.hibernate.ddl-auto=none"
})
class PersonServiceTest {

    // The Spring framework will automatically inject the necessary beans for PersonServiceImpl
    @Autowired
    PersonServiceImpl personService;

    // MockBean tells Spring to replace the bean of the same type in the application context with this mock.
    // This is useful when you want to isolate a part of your application under test from other parts.
    @MockBean
    PersonRepository personRepository;

    @Test
    @DisplayName("Given a person's name and age > 18, the person should be allowed adult content")
    void givenExistingPersonName_adultContentAllowed() throws BusinessException {
        // Prepare mock data for the test
        Person mockPerson = new Person();
        mockPerson.setName("Mock");
        mockPerson.setAge(19);
        when(personRepository.findByName(any())).thenReturn(Optional.of(mockPerson));

        assertTrue(personService.isAdultContentAllowed("dummy"), "Expected true as the person's age is > 18");
    }

    @Test
    @DisplayName("Given a person's name and age < 18, the person should not be allowed adult content")
    void givenExistingPersonName_adultContentNotAllowed() throws BusinessException {
        // Prepare mock data for the test
        Person mockPerson = new Person();
        mockPerson.setName("Mock");
        mockPerson.setAge(10);
        when(personRepository.findByName(any())).thenReturn(Optional.of(mockPerson));

        assertFalse(personService.isAdultContentAllowed("dummy"), "Expected false as the person's age is < 18");
    }

    @Test
    @DisplayName("If a person's name is not found, an exception should be thrown")
    void givenNonExistingPersonName_adultContentNotAllowed() {
        when(personRepository.findByName(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(BusinessException.class, () -> personService.isAdultContentAllowed("dummy"));

        assertTrue(exception.getMessage().contains(String.format("Person with name '%s' not found", "dummy")), "Exception message must be as expected");
    }

    @Test
    @DisplayName("If the provided person's name is empty, an exception should be thrown")
    void givenEmptyPersonName_adultContentNotAllowed() {
        Exception exception = assertThrows(BusinessException.class, () -> personService.isAdultContentAllowed(null));

        assertTrue(exception.getMessage().contains("Person name cannot be null."), "Exception message must be as expected");
    }
}
