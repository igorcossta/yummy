package io.igorcossta.recipe;

public class RecipeDisabledException extends RuntimeException {
    public RecipeDisabledException(String message) {
        super(message);
    }
}
