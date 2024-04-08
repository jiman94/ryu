package com.maemresen.junit.ut.mockito;

import com.maemresen.junit.ut.mockito.service.PersonService;
import com.maemresen.junit.ut.mockito.service.domain.Person;
import com.maemresen.junit.ut.mockito.service.impl.PersonServiceImpl;
import com.maemresen.junit.ut.mockito.service.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Indicate that Mockito should process this class for annotations
class VerifyExampleTest {

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

    @Test
    @DisplayName("Given existing person name and above age 18, when isAdultContentAllowed, then true")
    void givenExistingPersonNameAndAboveAge18_whenIsAdultContentAllowed_thenTrue() {
        // Sample data for the test
        final var personName = "Mock";
        final var mockPerson = new Person("Mock", 19);

        // Mocking the repository response for the test scenario
        when(personRepository.findByName(personName)).thenReturn(Optional.of(mockPerson));

        assertAll(
                // Assert that the service behaves correctly given the mocked data
                () -> assertTrue(personService.isAdultContentAllowed(personName), "Adult content should be allowed"),
                // Verifying that the repository's findByName method was called with the expected parameter
                () -> verify(personRepository).findByName(personName)
        );
    }
}
