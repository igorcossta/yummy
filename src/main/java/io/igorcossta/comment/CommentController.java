package io.igorcossta.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/post")
    public String recipes(@ModelAttribute("commentDto") CommentCreationDTO comment, @RequestParam long recipeId) {
        commentService.postComment(comment, recipeId);
        return "redirect:/recipes/details/" + recipeId;
    }
}
