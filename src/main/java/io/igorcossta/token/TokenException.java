package io.igorcossta.token;

import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {
    private final String exceptionType;

    public TokenException(String message, String exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }
}
