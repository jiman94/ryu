package com.maemresen.junit.ut.mockito;

import com.maemresen.junit.ut.mockito.service.PersonService;
import com.maemresen.junit.ut.mockito.service.domain.Person;
import com.maemresen.junit.ut.mockito.service.exception.BusinessException;
import com.maemresen.junit.ut.mockito.service.impl.PersonServiceImpl;
import com.maemresen.junit.ut.mockito.service.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WhenThenExampleTest {

    // The service we want to test
    PersonService personService;

    // Mocking the repository to isolate the service for unit testing
    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void init() {
        // Initializing the service with the mocked repository before each test
        personService = new PersonServiceImpl(personRepository);
    }

    // Multiple test scenarios covering various edge cases and expected behaviors:

    @Test
    @DisplayName("Given existing person name and above age 18, when isAdultContentAllowed, then true")
    void givenExistingPersonNameAndAboveAge18_whenIsAdultContentAllowed_thenTrue() throws BusinessException {
        // Sample data for the test
        final var personName = "Mock";
        final var mockPerson = new Person("Mock", 19);

        // Mocking the repository response for the test scenario
        when(personRepository.findByName(personName)).thenReturn(Optional.of(mockPerson));

        assertTrue(personService.isAdultContentAllowed(personName), "Adult content should be allowed");
    }

    @Test
    @DisplayName("Given existing person name and below age 18, when isAdultContentAllowed, then false")
    void givenExistingPersonNameAndBelowAge18_whenIsAdultContentAllowed_thenFalse() throws BusinessException {
        Person mockPerson = new Person("Mock", 10);
        when(personRepository.findByName(any())).thenReturn(Optional.of(mockPerson));

        assertFalse(personService.isAdultContentAllowed("dummy"), "Adult content should not be allowed");
    }

    @Test
    @DisplayName("Given non existing person name, when isAdultContentAllowed, then throw exception")
    void givenNonExistingPersonName_whenIsAdultContentAllowed_thenThrowException() {
        when(personRepository.findByName(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(BusinessException.class, () -> personService.isAdultContentAllowed("dummy"));

        assertTrue(exception.getMessage().contains(String.format("Person with name '%s' not found", "dummy")), "Exception message must be as expected");
    }

    @Test
    @DisplayName("Given empty person name, when isAdultContentAllowed, then throw exception")
    void givenEmptyPersonName_whenIsAdultContentAllowed_thenThrowException() {
        Exception exception = assertThrows(BusinessException.class, () -> personService.isAdultContentAllowed(null));

        assertTrue(exception.getMessage().contains("Person name cannot be null."), "Exception message must be as expected");
    }
}
