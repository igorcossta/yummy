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
    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "Email address cannot be empty")
    @Size(max = 100, message = "Email address must be at most 100 characters")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 120, message = "Password must be at most 120 characters")
    private String password;
}
