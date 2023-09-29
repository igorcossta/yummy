package io.igorcossta.comment;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public HtmxResponse postComment(@Valid @ModelAttribute("commentCreation") CommentCreationDTO comment,
                                    BindingResult bindingResult,
                                    @RequestParam long recipeId) {
        if (bindingResult.hasErrors()) {
            return new HtmxResponse()
                    .addTemplate(new ModelAndView("recipe/recipe-details :: form", Map.of("id", recipeId)));
        }

        return new HtmxResponse()
                .addTemplate(new ModelAndView("fragment/comment :: newComment", Map.of("data",
                        commentService.postComment(comment, recipeId))));
    }
}
