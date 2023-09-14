package io.igorcossta.recipe;

import io.igorcossta.calories.Calories;
import io.igorcossta.calories.CaloriesService;
import io.igorcossta.comment.Comment;
import io.igorcossta.comment.CommentRepository;
import io.igorcossta.comment.CommentViewDTO;
import io.igorcossta.user.User;
import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final CommentRepository commentRepository;
    private final CaloriesService caloriesService;

    public long createRecipe(RecipeCreationDTO recipe) {
        User user = UserService.getPrincipal();
        Recipe toSave = Recipe.fromRecipeCreationDTO(recipe, user);

        Recipe saved = recipeRepository.save(toSave);
        return saved.getId();
    }

    public List<RecipeViewDTO> listMyRecipes() {
        User user = UserService.getPrincipal();
        return recipeRepository.findAllByRecipeOwner(user)
                .stream()
                .map(Recipe::toRecipeViewDTO)
                .collect(Collectors.toList());
    }

    public List<RecipeViewDTO> findAll() {
        return recipeRepository.findAll()
                .stream()
                .map(Recipe::toRecipeViewDTO)
                .collect(Collectors.toList());
    }

    public RecipeViewDTO getRecipeViewDTO(Long id) {
        Recipe recipe = getRecipeById(id);
        return Recipe.toRecipeViewDTO(recipe);
    }

    public Recipe getRecipeEntity(Long id) {
        return getRecipeById(id);
    }

    private Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public RecipeAndCommentsViewDTO getRecipeAndComments(Long id) {
        RecipeViewDTO recipeViewDTO = getRecipeViewDTO(id);

        Calories calories = caloriesService.getCaloriesFor(recipeViewDTO.ingredients()).block();

        List<CommentViewDTO> comments = commentRepository.findAllByRecipeId(id)
                .stream()
                .map(Comment::toCommentViewDTO)
                .collect(Collectors.toList());
        return new RecipeAndCommentsViewDTO(recipeViewDTO, comments, calories);
    }
}
