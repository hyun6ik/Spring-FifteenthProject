package com.example.spring_login_register_email.service;

import com.example.spring_login_register_email.registration.RegistrationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RegistrationService {


    public String register(RegistrationRequest request) {

        return "It works";
    }
}
