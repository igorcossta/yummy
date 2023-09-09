package io.igorcossta.recipe;

import io.igorcossta.comment.Comment;
import io.igorcossta.comment.CommentRepository;
import io.igorcossta.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final CommentRepository commentRepository;

    public void createRecipe(CreateNewRecipe recipe) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Recipe toSave = new Recipe(user, recipe.getName(), recipe.getDescription(), recipe.getIngredients(), recipe.getHowToPrepare());

        log.debug(user.getUsername());
        recipeRepository.save(toSave);
    }

    public List<Recipe> listMyRecipes() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.debug(user.getUsername());
        return recipeRepository.findAllByRecipeOwner(user);
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipe(Long recipe) {
        return recipeRepository.findById(recipe).orElseThrow(() -> new RuntimeException("recipe not found"));
    }

    public RecipeAndComments getRecipeAndComments(Long id) {
        Recipe recipe = getRecipe(id);
        List<Comment> comments = commentRepository.findAllByRecipeId(id);
        return new RecipeAndComments(recipe, comments);
    }
}
