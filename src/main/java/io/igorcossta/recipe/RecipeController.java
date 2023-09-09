package io.igorcossta.recipe;

import io.igorcossta.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/receitas")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public String recipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipe/show-all-recipes";
    }

    @GetMapping("/publicadas")
    public String listMyRecipes(Model model) {
        List<Recipe> recipes = recipeService.listMyRecipes();
        model.addAttribute("recipes", recipes);
        return "user/recipe/show-my-recipes";
    }

    @GetMapping("/compartilhar")
    public String createRecipeHomePage(Model model) {
        model.addAttribute("recipe", new CreateNewRecipe());
        return "user/recipe/create-new-recipe";
    }

    @PostMapping("/compartilhar")
    public String createRecipe(Model model, @ModelAttribute CreateNewRecipe recipe) {
        log.debug(recipe.toString());
        recipeService.createRecipe(recipe);
        return "redirect:/?c1001";
    }

    @GetMapping("/detalhes/{receita}")
    public String displayFoodRecipe(Model model, @PathVariable Long receita) {
        RecipeAndComments recipe = recipeService.getRecipeAndComments(receita);

        model.addAttribute("recipeAndComments", recipe);
        model.addAttribute("commentDto", new CommentDto());
        return "recipe/recipe-details";
    }
}
