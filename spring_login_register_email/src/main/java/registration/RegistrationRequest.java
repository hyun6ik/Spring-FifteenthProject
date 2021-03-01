package registration;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
