package io.igorcossta.registration;

public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String message, String username) {
        super(message.formatted(username));
    }
}
