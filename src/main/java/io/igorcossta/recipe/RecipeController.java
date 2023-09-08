package io.igorcossta.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "recipes";
    }

    @GetMapping("/publicadas")
    public String listMyRecipes(Model model) {
        List<Recipe> recipes = recipeService.listMyRecipes();
        model.addAttribute("recipes", recipes);
        return "list-my-recipes";
    }

    @GetMapping("/compartilhar")
    public String createRecipeHomePage(Model model) {
        model.addAttribute("recipe", new CreateNewRecipe());
        return "create-new-recipe";
    }

    @PostMapping("/compartilhar")
    public String createRecipe(Model model, @ModelAttribute CreateNewRecipe recipe) {
        log.debug(recipe.toString());
        recipeService.createRecipe(recipe);
        return "redirect:/?c1001";
    }
}
