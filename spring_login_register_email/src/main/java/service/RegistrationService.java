package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import registration.RegistrationRequest;

@Service
@Transactional(readOnly = true)
public class RegistrationService {

    public String register(RegistrationRequest request) {
        return "works";
    }
}
