package io.igorcossta.token;

import lombok.Getter;

@Getter
public enum TokenType {
    ACTIVATE("token:activate_account");

    private final String tokenType;

    TokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
