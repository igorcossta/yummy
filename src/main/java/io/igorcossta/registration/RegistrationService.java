package io.igorcossta.registration;

import io.igorcossta.mail.MailService;
import io.igorcossta.token.Token;
import io.igorcossta.token.TokenService;
import io.igorcossta.user.User;
import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final TokenService tokenService;
    private final MailService mailService;

    @Transactional
    public void registerNewUser(final UserRegistrationDTO newUser) {
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            throw new PasswordNotMatchException("password and confirm password are not the same");
        }
        User entity = userService.save(newUser);
        Token token = tokenService.create(entity);
        mailService.sendMail(entity, token);
    }
}
