package com.example.spring_login_register_email.service;

import com.example.spring_login_register_email.appuser.AppUser;
import com.example.spring_login_register_email.appuser.AppUserRole;
import com.example.spring_login_register_email.registration.EmailValidator;
import com.example.spring_login_register_email.registration.RegistrationRequest;
import com.example.spring_login_register_email.registration.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidator emailValidator;

    @Transactional
    public String register(RegistrationRequest request) {
        System.out.println(request);
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalArgumentException("이메일이 유효하지 않습니다");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("토큰을 찾을 수 없습니다"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("이미 확인된 이메일입니다");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("토큰이 만료되었습니다");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}
