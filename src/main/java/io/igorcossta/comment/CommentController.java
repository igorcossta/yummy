package io.igorcossta.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{recipeId}/share")
    public ModelAndView processCommentForm(@Valid @ModelAttribute("comment") CommentCreationDTO comment,
                                           BindingResult bindingResult,
                                           @PathVariable long recipeId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("recipe/recipe-details :: form", Map.of("recipeId", recipeId), BAD_REQUEST);
        }
        return new ModelAndView("fragment/comment :: newComment", Map.of("data",
                commentService.postComment(comment, recipeId)), CREATED);
    }
}
