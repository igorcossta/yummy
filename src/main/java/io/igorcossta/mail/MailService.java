package io.igorcossta.mail;

import io.igorcossta.token.Token;
import io.igorcossta.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async
    public void sendMail(User to, final Token token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Account verification");
            message.setFrom(fromEmail);
            message.setTo(to.getUsername());
            message.setText("""
                    Dear %s,
                    Thank you for creating an account with us! To ensure the security of your account and complete the registration process, we require you to verify your email address.

                    Please click on the link below to verify your account:
                    %s

                    If you cannot click the link, please copy and paste it into your web browser's address bar.
                    Please note that this link is valid for the next 24 hours only. After that, you will need to request a new verification link.

                    Best regards,
                    The Yummy Team
                    """.formatted(to.getUsername(), token.getTokenURL() + token.getToken()));
            sender.send(message);
        } catch (MailException ex) {
            log.error("verification email send error", ex);
        }
    }
}
