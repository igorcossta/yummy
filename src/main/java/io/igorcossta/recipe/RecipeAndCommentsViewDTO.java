package io.igorcossta.recipe;

import io.igorcossta.comment.CommentViewDTO;

import java.util.List;

public record RecipeAndCommentsViewDTO(RecipeViewDTO recipe, List<CommentViewDTO> comments) {
}
