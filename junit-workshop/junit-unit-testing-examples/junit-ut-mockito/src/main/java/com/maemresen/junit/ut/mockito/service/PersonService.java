package com.maemresen.junit.ut.mockito.service;

import com.maemresen.junit.ut.mockito.service.exception.BusinessException;

public interface PersonService {
    boolean isAdultContentAllowed(String personName) throws BusinessException;
}
