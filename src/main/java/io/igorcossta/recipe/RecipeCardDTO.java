package io.igorcossta.recipe;

import java.time.ZonedDateTime;

public record RecipeCardDTO(Long id, String title, String description, ZonedDateTime createdAt) {
}
