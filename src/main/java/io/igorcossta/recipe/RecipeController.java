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

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    @ResponseStatus(OK)
    public String recipes(Model model, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("data", recipeService.searchForAllRecipes(page));
        return "recipe/show-all-recipes";
    }

    @PostMapping("/new")
    public ModelAndView processCreationForm(@Valid @ModelAttribute("recipe") RecipeCreationDTO recipe,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("dashboard/create-new-recipe", BAD_REQUEST);
        }
        return new ModelAndView("redirect:/recipes/%s/details".formatted(recipeService.createRecipe(recipe)));
    }

    @PostMapping("/{recipeId}/edit")
    public ModelAndView processUpdateForm(@Valid @ModelAttribute("recipe") RecipeEditDTO recipe,
                                          BindingResult bindingResult,
                                          @PathVariable long recipeId) {
        Recipe original = recipeService.isResourceOwner(recipeId);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("dashboard/update-recipe", BAD_REQUEST);
        }
        recipeService.updateMyRecipe(recipe, original);
        return new ModelAndView("redirect:/dashboard/recipes");
    }

    @GetMapping("/{recipeId}/details")
    @ResponseStatus(OK)
    public String viewRecipeById(Model model, @PathVariable long recipeId) {
        model.addAttribute("recipeAndComments", recipeService.searchForRecipeAndComments(recipeId));
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("comment", new CommentCreationDTO());
        return "recipe/recipe-details";
    }

    @DeleteMapping("/{recipeId}")
    @ResponseStatus(OK)
    public HtmxResponse deleteRecipeById(@PathVariable long recipeId) {
        recipeService.disableMyRecipe(recipeId);
        return new HtmxResponse()
                .addTemplate(new ModelAndView("fragment/shared/toast",
                        Map.of("msg", "Recipe %s deleted successfully!".formatted(recipeId))))
                .addTemplate(new ModelAndView(
                        "dashboard/show-my-recipes :: recipes-container",
                        Map.of("recipes", recipeService.searchForMyRecipes(1))));
    }
}
