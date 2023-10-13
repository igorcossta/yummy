package io.igorcossta.token;

public class TokenExpiredException extends TokenException {

    public TokenExpiredException(String message, String token) {
        super(message.formatted(token), "TOKEN EXPIRED");
    }
}
