package io.igorcossta.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegistrationDTO {
    @Email(message = "{validation.messages.email}")
    @NotEmpty(message = "{validation.messages.email.notEmpty}")
    @Size(max = 100, message = "{validation.messages.email.size}")
    private String username;

    @NotEmpty(message = "{validation.messages.password.notEmpty}")
    @Size(max = 120, message = "{validation.messages.password.size}")
    private String password;

    @NotEmpty(message = "{validation.messages.confirmPassword.notEmpty}")
    @Size(max = 120, message = "{validation.messages.confirmPassword.size}")
    private String confirmPassword;
}
