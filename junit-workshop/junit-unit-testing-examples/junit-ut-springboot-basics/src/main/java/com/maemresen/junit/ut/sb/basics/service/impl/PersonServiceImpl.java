package com.maemresen.junit.ut.sb.basics.service.impl;


import com.maemresen.junit.ut.sb.basics.service.PersonService;
import com.maemresen.junit.ut.sb.basics.service.domain.Person;
import com.maemresen.junit.ut.sb.basics.service.exception.BusinessException;
import com.maemresen.junit.ut.sb.basics.service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public boolean isAdultContentAllowed(String personName) throws BusinessException {
        if (personName == null) {
            throw new BusinessException("Person name cannot be null.");
        }

        Person person = personRepository.findByName(personName).orElseThrow(() -> new BusinessException(String.format("Person with name '%s' not found", personName)));
        int age = person.getAge();
        return age > 18;
    }
}
