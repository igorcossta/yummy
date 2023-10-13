package io.igorcossta.dashboard;

import io.igorcossta.recipe.Recipe;
import io.igorcossta.recipe.RecipeCreationDTO;
import io.igorcossta.recipe.RecipeMapper;
import io.igorcossta.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    @GetMapping
    @ResponseBody
    public String dashboard() {
        return "Dashboard";
    }

    @GetMapping("/recipes")
    public String recipes(Model model) {
        model.addAttribute("recipes", recipeService.searchForMyRecipes());
        return "dashboard/show-my-recipes";
    }

    @GetMapping("/recipes/new")
    @ResponseStatus(OK)
    public String initCreationForm(Model model) {
        model.addAttribute("recipe", new RecipeCreationDTO());
        return "dashboard/create-new-recipe";
    }

    @GetMapping("/recipes/{recipeId}/edit")
    @ResponseStatus(OK)
    public String initUpdateForm(@PathVariable long recipeId,
                                 Model model) {
        Recipe recipe = recipeService.isResourceOwner(recipeId);
        model.addAttribute("recipe", recipeMapper.entityToRecipeEditDto(recipe));
        return "dashboard/update-recipe";
    }
}
