package io.igorcossta.recipe;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.igorcossta.comment.CommentCreationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public String recipes(Model model) {
        model.addAttribute("recipes", recipeService.searchForAllRecipes());
        return "recipe/show-all-recipes";
    }

    @GetMapping("/mines")
    public String listMyRecipes(Model model) {
        model.addAttribute("recipes", recipeService.searchForMyRecipes());
        return "user/recipe/show-my-recipes";
    }

    @GetMapping("/share")
    public String createRecipeHomePage(Model model) {
        model.addAttribute("recipe", new RecipeCreationDTO());
        return "user/recipe/create-new-recipe";
    }

    @PostMapping("/share")
    public String createRecipe(@Valid @ModelAttribute("recipe") RecipeCreationDTO recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/recipe/create-new-recipe";
        }

        return "redirect:/recipes/details/" + recipeService.createRecipe(recipe);
    }

    @GetMapping("/details/{id}")
    public String displayFoodRecipe(Model model, @PathVariable Long id) {
        model.addAttribute("recipeAndComments", recipeService.searchForRecipeAndComments(id));
        return "recipe/recipe-details";
    }

    @DeleteMapping("/{id}")
    public HtmxResponse deleteRecipe(@PathVariable Long id) {
        recipeService.disableMyRecipe(id);
        return new HtmxResponse()
                .addTemplate(new ModelAndView("fragment/shared/toast",
                        Map.of("msg", "Recipe %s deleted successfully!".formatted(id))))
                .addTemplate(new ModelAndView(
                        "user/recipe/show-my-recipes :: recipes-container",
                        Map.of("recipes", recipeService.searchForMyRecipes())));
    }

    @GetMapping("/edit/{id}")
    public String updateRecipe(Model model, @PathVariable Long id) {
        RecipeCreationDTO recipe = Recipe.toRecipeCreationDTO(recipeService.searchForRecipe(id));
        model.addAttribute("recipe", recipe);
        return "user/recipe/update-recipe";
    }

    @PutMapping("/edit/{id}")
    public HtmxResponse updateRecipe(@Valid @ModelAttribute("recipe") RecipeCreationDTO recipe,
                                     BindingResult bindingResult,
                                     @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return new HtmxResponse()
                    .addTemplate("user/recipe/update-recipe :: form");
        }
        recipeService.updateMyRecipe(id, recipe);
        return new HtmxResponse()
                .browserRedirect("/recipes/mines");
    }

    @ModelAttribute("commentCreation")
    public CommentCreationDTO commentCreation() {
        return new CommentCreationDTO();
    }
}
