package io.igorcossta.recipe;

import io.igorcossta.calories.Calories;
import io.igorcossta.comment.CommentViewDTO;

import java.util.List;

public record RecipeAndCommentsViewDTO(RecipeDetailsDTO recipe, List<CommentViewDTO> comments, Calories calories) {
}
