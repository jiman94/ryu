package com.maemresen.it.sb.with.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AuditRepository auditRepository;

    @Transactional
    public void create(String auditUsername, Person person) {
        createRecord(auditUsername, person);
    }

    public void createWithoutTransaction(String auditUsername, Person person) {
        createRecord(auditUsername, person);
    }

    private void createRecord(String auditUsername, Person person) {
        person = personRepository.save(person);
        auditRepository.save(Audit.builder()
                .username(auditUsername)
                .objectId(person.getUsername())
                .createdDate(LocalDateTime.now())
                .action(AuditUtils.AUDIT_ACTION_CREATE_PERSON)
                .build());
    }

    public Optional<Person> findByUsername(String username) {
        return personRepository.findByUsername(username);
    }
}
