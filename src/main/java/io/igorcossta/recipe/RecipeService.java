package io.igorcossta.recipe;

import io.igorcossta.calories.Calories;
import io.igorcossta.calories.CaloriesService;
import io.igorcossta.comment.CommentDTO;
import io.igorcossta.comment.CommentRepository;
import io.igorcossta.misc.Pagination;
import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public RecipeAndCommentsDTO searchForRecipeAndComments(Long id) {
        RecipeDetailsDTO recipeDetails = recipeMapper.entityToRecipeDetailsDto(searchForRecipe(id));

        Calories calories = caloriesService.getCaloriesFor(recipeDetails.ingredients()).block();
        if (calories == null) calories = new Calories();

        List<CommentDTO> comments = commentRepository.findAllByRecipeId(id)
                .stream()
                .sorted(Comparator.comparing(CommentDTO::createdAt).reversed())
                .collect(Collectors.toList());
        return new RecipeAndCommentsDTO(recipeDetails, comments, calories);
    }

    @Transactional
    public void disableMyRecipe(Long id) {
        isResourceOwner(id);
        recipeRepository.disableRecipeById(id);
    }

    @Transactional(readOnly = true)
    public Pagination<RecipeCardDTO> searchForMyRecipes(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<RecipeCardDTO> pages = recipeRepository.findAllActiveRecipesByOwner(UserService.getPrincipal(), pageable);
        List<RecipeCardDTO> content = pages
                .stream()
                .sorted(Comparator.comparing(RecipeCardDTO::createdAt).reversed())
                .collect(Collectors.toList());
        return new Pagination<>(content,
                pages.getNumber() + 1,
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getSize());
    }

    @Transactional(readOnly = true)
    public Pagination<RecipeCardDTO> searchForAllRecipes(int page) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<RecipeCardDTO> pages = recipeRepository.findAllActiveRecipes(pageable);
        List<RecipeCardDTO> content = pages.getContent()
                .stream()
                .sorted(Comparator.comparing(RecipeCardDTO::createdAt).reversed())
                .collect(Collectors.toList());
        return new Pagination<>(content,
                pages.getNumber() + 1,
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getSize());
    }

    @Transactional
    public void updateMyRecipe(RecipeEditDTO dto, Recipe original) {
        recipeRepository.save(recipeMapper.updateEntity(original, dto));
    }

    @Transactional(readOnly = true)
    public Recipe isResourceOwner(Long id) {
        Recipe recipe = searchForRecipe(id);
        String username = recipe.getRecipeOwner().getUsername();
        if (!username.equals(UserService.getPrincipal().getUsername()))
            throw new RecipeNotOwnerException("%s are not the owner of recipe %s".formatted(username, id));
        return recipe;
    }
}
