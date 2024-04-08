package com.maemresen.junit.ut.sb.with.h2.service;


import com.maemresen.junit.ut.sb.with.h2.service.exception.BusinessException;

public interface PersonService {
    boolean isAdultContentAllowed(String personName) throws BusinessException;
}
