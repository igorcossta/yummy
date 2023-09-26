package io.igorcossta.recipe;

import io.igorcossta.calories.Calories;
import io.igorcossta.comment.CommentDTO;

import java.util.List;

public record RecipeAndCommentsDTO(RecipeDetailsDTO recipe, List<CommentDTO> comments, Calories calories) {
}
