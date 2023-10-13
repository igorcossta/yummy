package io.igorcossta.token;

import io.igorcossta.user.User;
import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Value("${spring.mail.verify-host}")
    private String verify_mail;
    private final TokenRepository tokenRepository;
    private final UserService userService;

    @Transactional
    public Token create(User tokenOwner) {
        String uuid = UUID.randomUUID().toString();
        boolean isPresent = tokenRepository.existsByToken(uuid);
        if (isPresent) {
            throw new RuntimeException("activation token already exists");
        }
        Token token = Token.builder()
                .userId(tokenOwner.getId())
                .token(uuid)
                .tokenType(TokenType.ACTIVATE)
                .expiresAt(LocalDateTime.now().plusHours(24L))
                .isActivate(false)
                .tokenURL(verify_mail)
                .build();
        tokenRepository.save(token);
        return token;
    }

    @Transactional
    public void activateToken(String requestToken) {
        Token token = tokenRepository.findTokenByToken(requestToken)
                .orElseThrow(() -> new TokenNotFoundException("token %s not found", requestToken));
        if (token.isActivate()) {
            throw new TokenInvalidException("token %s is already activated", requestToken);
        }
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("token %s expired", requestToken);
        }
        token.setActivate(true);
        tokenRepository.save(token);
        userService.activateUserById(token.getUserId());
    }
}
