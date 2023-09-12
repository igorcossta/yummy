package io.igorcossta.recipe;

import io.igorcossta.comment.CommentCreationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public String recipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipe/show-all-recipes";
    }

    @GetMapping("/mines")
    public String listMyRecipes(Model model) {
        List<RecipeViewDTO> recipes = recipeService.listMyRecipes();
        model.addAttribute("recipes", recipes);
        return "user/recipe/show-my-recipes";
    }

    @GetMapping("/share")
    public String createRecipeHomePage(Model model) {
        model.addAttribute("recipe", new RecipeCreationDTO());
        return "user/recipe/create-new-recipe";
    }

    @PostMapping("/share")
    public String createRecipe(@ModelAttribute RecipeCreationDTO recipe) {
        long id = recipeService.createRecipe(recipe);
        return "redirect:/recipes/details/" + id;
    }

    @GetMapping("/details/{id}")
    public String displayFoodRecipe(Model model, @PathVariable Long id) {
        RecipeAndCommentsViewDTO recipe = recipeService.getRecipeAndComments(id);

        model.addAttribute("recipeAndComments", recipe);
        return "recipe/recipe-details";
    }

    @ModelAttribute("commentCreation")
    public CommentCreationDTO commentCreation() {
        return new CommentCreationDTO();
    }
}
