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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final CommentRepository commentRepository;
    private final CaloriesService caloriesService;
    private final RecipeMapper recipeMapper;

    @Transactional
    public long createRecipe(RecipeCreationDTO recipe) {
        Recipe toSave = recipeMapper.recipeCreationToEntity(recipe);
        return recipeRepository.save(toSave).getId();
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
        RecipeDetailsDTO recipeDetails = recipeMapper.entityToRecipeDetailsDto(searchForRecipe(id));

        Calories calories = caloriesService.getCaloriesFor(recipeDetails.ingredients()).block();
        if (calories == null) calories = new Calories();

        List<CommentViewDTO> comments = commentRepository.findAllByRecipeId(id)
                .stream()
                .map(Comment::toCommentViewDTO)
                .collect(Collectors.toList());
        return new RecipeAndCommentsViewDTO(recipeDetails, comments, calories);
    }

    @Transactional
    public void disableMyRecipe(Long id) {
        String username = UserService.getPrincipal().getUsername();
        if (!searchForRecipe(id).getRecipeOwner().getUsername().equals(username))
            throw new RecipeNotOwnerException("%s are not the owner of recipe %s".formatted(username, id));
        recipeRepository.disableRecipeById(id);
    }

    @Transactional(readOnly = true)
    public List<RecipeCardDTO> searchForMyRecipes() {
        return recipeRepository.findAllActiveRecipesByOwner(UserService.getPrincipal())
                .stream()
                .map(recipeMapper::entityToRecipeCardDto)
                .sorted(Comparator.comparing(RecipeCardDTO::id))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecipeCardDTO> searchForAllRecipes() {
        return recipeRepository.findAllActiveRecipes()
                .stream()
                .map(recipeMapper::entityToRecipeCardDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateMyRecipe(Long id, RecipeEditDTO dto) {
        String username = UserService.getPrincipal().getUsername();
        Recipe recipe = searchForRecipe(id);
        if (!recipe.getRecipeOwner().getUsername().equals(username))
            throw new RecipeNotOwnerException("%s are not the owner of recipe %s".formatted(username, id));
        recipeRepository.save(recipeMapper.updateEntity(recipe, dto));
    }
}
