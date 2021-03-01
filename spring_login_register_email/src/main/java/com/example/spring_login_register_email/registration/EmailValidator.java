package com.example.spring_login_register_email.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
//        ToDO : 이메일을 확인하는 정규식
        return true;
    }
}
