package io.igorcossta.recipe;

public class RecipeNotOwnerException extends RuntimeException {
    public RecipeNotOwnerException(String message) {
        super(message);
    }
}
