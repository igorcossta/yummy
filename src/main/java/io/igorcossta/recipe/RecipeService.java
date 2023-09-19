package io.igorcossta.recipe;

import io.igorcossta.calories.Calories;
import io.igorcossta.calories.CaloriesService;
import io.igorcossta.comment.Comment;
import io.igorcossta.comment.CommentRepository;
import io.igorcossta.comment.CommentViewDTO;
import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final CommentRepository commentRepository;
    private final CaloriesService caloriesService;

    @Transactional
    public long createRecipe(RecipeCreationDTO recipe) {
        Recipe toSave = Recipe.fromRecipeCreationDTO(recipe, UserService.getPrincipal());
        return recipeRepository.save(toSave).getId();
    }

    public Recipe getRecipeEntity(Long id) {
        return searchForRecipe(id);
    }

    @Transactional(readOnly = true)
    public Recipe searchForRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("recipe %s was not found".formatted(id)));
        if (recipe.isDisabled()) throw new RecipeDisabledException("recipe %s no longer exists".formatted(id));
        return recipe;
    }

    @Transactional(readOnly = true)
    public RecipeAndCommentsViewDTO searchForRecipeAndComments(Long id) {
        RecipeViewDTO recipeViewDTO = Recipe.toRecipeViewDTO(searchForRecipe(id));

        Calories calories = caloriesService.getCaloriesFor(recipeViewDTO.ingredients()).block();
        if (calories == null) calories = new Calories();

        List<CommentViewDTO> comments = commentRepository.findAllByRecipeId(id)
                .stream()
                .map(Comment::toCommentViewDTO)
                .collect(Collectors.toList());
        return new RecipeAndCommentsViewDTO(recipeViewDTO, comments, calories);
    }

    @Transactional
    public void disableMyRecipe(Long id) {
        boolean isDisabled = recipeRepository.isRecipeDisabled(id);
        if (isDisabled) throw new RecipeDisabledException("recipe %s is already disabled".formatted(id));
        recipeRepository.disableRecipeById(id);
    }

    @Transactional(readOnly = true)
    public List<RecipeViewDTO> searchForMyRecipes() {
        return recipeRepository.findAllActiveRecipesByOwner(UserService.getPrincipal())
                .stream()
                .map(Recipe::toRecipeViewDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecipeViewDTO> searchForAllRecipes() {
        return recipeRepository.findAllActiveRecipes()
                .stream()
                .map(Recipe::toRecipeViewDTO)
                .collect(Collectors.toList());
    }
}
