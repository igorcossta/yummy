package io.igorcossta.token;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expiresAt;

    private boolean isActivate;

    private String tokenURL;

    public Token(Long userId,
                 String token,
                 TokenType tokenType,
                 LocalDateTime expiresAt,
                 boolean isActivate,
                 String tokenURL) {
        this.userId = userId;
        this.token = token;
        this.tokenType = tokenType;
        this.expiresAt = expiresAt;
        this.isActivate = isActivate;
        this.tokenURL = tokenURL;
    }
}
