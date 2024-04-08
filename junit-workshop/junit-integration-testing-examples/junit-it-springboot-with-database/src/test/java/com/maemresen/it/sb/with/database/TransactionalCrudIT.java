package com.maemresen.it.sb.with.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = "spring.jpa.show-sql=true")
class TransactionalCrudIT {

    @Autowired
    private PersonService personService;

    @Autowired
    private AuditRepository auditRepository;

    // Utility method to generate a unique string. Useful for creating unique usernames and passwords.
    private String randomString() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String randomUsername() {
        return "johndoe" + randomString();
    }

    private String randomPassword() {
        return "password" + randomString();
    }

    @Test
    void testPersonSave() {
        String username = randomUsername();
        Person newUser = new Person(null, username, randomPassword());

        personService.create(AuditUtils.AUDIT_SYSTEM_USER, newUser);

        Optional<Person> foundPerson = personService.findByUsername(username);
        assertTrue(foundPerson.isPresent(), "Newly registered person should be found in the database");

        List<Audit> foundPersonAudits = auditRepository.findByUsernameAndObjectId(AuditUtils.AUDIT_SYSTEM_USER, username);
        assertEquals(username, foundPerson.get().getUsername(), "Registered and found persons should have the same username");
        assertEquals(1, foundPersonAudits.size(), "There should be exactly one audit entry for the newly registered person");

        Audit audit = foundPersonAudits.get(0);
        assertEquals(AuditUtils.AUDIT_ACTION_CREATE_PERSON, audit.getAction(), "Audit entry should have the correct action");
        assertEquals(username, audit.getObjectId(), "Audit entry should have the correct object ID");
    }

    @Test
    void shouldRollbackWhenException() {
        String username = randomUsername();
        Person newUser = new Person(null, username, randomPassword());

        assertThrows(DataIntegrityViolationException.class, () -> personService.create(null, newUser), "Registration should throw an exception");

        Optional<Person> foundPerson = personService.findByUsername(username);
        assertTrue(foundPerson.isEmpty(), "Newly registered person should not be found in the database");

        boolean anyAuditFound = auditRepository.findAll().stream()
                .anyMatch(audit -> audit.getAction().equals(AuditUtils.AUDIT_ACTION_CREATE_PERSON) && audit.getObjectId().equals(username));
        assertFalse(anyAuditFound, "There should be no audit entry for the newly registered person");
    }

    @Test
    void shouldNotRollbackWhenException() {
        String username = randomUsername();
        Person newUser = new Person(null, username, randomPassword());

        assertThrows(DataIntegrityViolationException.class, () -> personService.createWithoutTransaction(null, newUser), "Registration should throw an exception");

        Optional<Person> foundPerson = personService.findByUsername(username);
        assertTrue(foundPerson.isPresent(), "Newly registered person should be found in the database");

        boolean anyAuditFound = auditRepository.findAll().stream()
                .anyMatch(audit -> audit.getAction().equals(AuditUtils.AUDIT_ACTION_CREATE_PERSON) && audit.getObjectId().equals(username));
        assertFalse(anyAuditFound, "There should be an audit entry for the newly registered person");
    }

    @Test
    void testDuplicateUsernameRegistration() {
        // Register the first person
        String username = randomUsername();
        Person firstPerson = new Person(null, username, randomPassword());
        personService.create(AuditUtils.AUDIT_SYSTEM_USER, firstPerson);

        // Attempt to register another person with the same username to check the uniqueness constraint
        Person duplicatePerson = new Person(null, username, randomPassword());

        // We expect a DataIntegrityViolationException since usernames should be unique in our system
        assertThrows(DataIntegrityViolationException.class, () -> personService.createWithoutTransaction(AuditUtils.AUDIT_SYSTEM_USER, duplicatePerson), "Registration with a duplicate username should throw an exception");
    }

    @Test
    void testRegistrationWithMissingData() {
        // Create person instances with missing crucial data
        Person personMissingUsername = new Person(null, null, "password123");
        Person personMissingPassword = new Person(null, "johndoe", null);

        // We expect DataIntegrityViolationException since our database should enforce the presence of these fields
        assertAll("Registration with missing data should throw an exception",
                () -> assertThrows(DataIntegrityViolationException.class, () -> personService.create(AuditUtils.AUDIT_SYSTEM_USER, personMissingUsername), "Registration with a missing username should throw an exception"),
                () -> assertThrows(DataIntegrityViolationException.class, () -> personService.create(AuditUtils.AUDIT_SYSTEM_USER, personMissingPassword), "Registration with a missing password should throw an exception"));
    }
}
