package io.igorcossta.token;

public class TokenNotFoundException extends TokenException {

    public TokenNotFoundException(String message, String token) {
        super(message.formatted(token), "TOKEN NOT FOUND");
    }
}
