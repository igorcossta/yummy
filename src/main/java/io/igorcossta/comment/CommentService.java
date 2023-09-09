package io.igorcossta.comment;

import io.igorcossta.recipe.Recipe;
import io.igorcossta.recipe.RecipeService;
import io.igorcossta.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final RecipeService recipeService;

    public void postComment(CommentDto comment, long recipeId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Recipe recipe = recipeService.getRecipe(recipeId);
        Comment toSave = new Comment(user, recipe, comment.getComment());
        commentRepository.save(toSave);
    }
}
