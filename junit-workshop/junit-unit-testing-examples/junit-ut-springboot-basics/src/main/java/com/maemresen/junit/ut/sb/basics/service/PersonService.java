package com.maemresen.junit.ut.sb.basics.service;


import com.maemresen.junit.ut.sb.basics.service.exception.BusinessException;

public interface PersonService {
    boolean isAdultContentAllowed(String personName) throws BusinessException;
}
