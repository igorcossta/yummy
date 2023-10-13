package io.igorcossta.token;

public class TokenInvalidException extends TokenException {

    public TokenInvalidException(String message, String token) {
        super(message.formatted(token), "TOKEN ALREADY ACTIVATED");
    }
}
