package com.example.spring_login_register_email.registration;


import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class RegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

}
