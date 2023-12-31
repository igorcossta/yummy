package io.igorcossta.recipe;

import java.time.ZonedDateTime;

public record RecipeDetailsDTO(
        Long id,
        String owner,
        String title,
        String description,
        String ingredients,
        String howToPrepare,
        ZonedDateTime createdAt,
        int preparationTime,
        int servings
) {
}
