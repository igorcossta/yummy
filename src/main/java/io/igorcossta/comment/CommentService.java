package io.igorcossta.comment;

import io.igorcossta.recipe.Recipe;
import io.igorcossta.recipe.RecipeService;
import io.igorcossta.user.User;
import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final RecipeService recipeService;

    public void postComment(CommentCreationDTO comment, long recipeId) {
        User user = UserService.getPrincipal();
        Recipe recipe = recipeService.searchForRecipe(recipeId);
        Comment toSave = new Comment(user, recipe, comment.getComment());
        commentRepository.save(toSave);
    }
}
