package io.igorcossta.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comentario")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/postar")
    public String recipes(Model model, @ModelAttribute("commentDto") CommentDto comment, @RequestParam long recipeId) {
        commentService.postComment(comment, recipeId);
        return "redirect:/receitas/detalhes/" + recipeId;
    }
}
