package io.igorcossta.comment;

import io.igorcossta.recipe.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final RecipeService recipeService;

    @PostMapping("/post")
    public String recipes(@Valid @ModelAttribute("commentCreation") CommentCreationDTO comment,
                          BindingResult bindingResult,
                          @RequestParam long recipeId,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("recipeAndComments", recipeService.getRecipeAndComments(recipeId));
            return "recipe/recipe-details";
        }

        commentService.postComment(comment, recipeId);
        return "redirect:/recipes/details/" + recipeId;
    }
}
