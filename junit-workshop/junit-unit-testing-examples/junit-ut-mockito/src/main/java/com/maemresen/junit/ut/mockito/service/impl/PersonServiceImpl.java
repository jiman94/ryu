package com.maemresen.junit.ut.mockito.service.impl;

import com.maemresen.junit.ut.mockito.service.PersonService;
import com.maemresen.junit.ut.mockito.service.domain.Person;
import com.maemresen.junit.ut.mockito.service.exception.BusinessException;
import com.maemresen.junit.ut.mockito.service.repository.PersonRepository;

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean isAdultContentAllowed(String personName) throws BusinessException {
        if (personName == null) {
            throw new BusinessException("Person name cannot be null.");
        }

        final Person person = personRepository.findByName(personName)
                .orElseThrow(() -> new BusinessException(String.format("Person with name '%s' not found", personName)));
        final int age = person.getAge();
        return age > 18;
    }
}
