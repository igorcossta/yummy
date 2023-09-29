package io.igorcossta.comment;

import io.igorcossta.recipe.RecipeService;
import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final RecipeService recipeService;

    public CommentDTO postComment(CommentCreationDTO comment, long recipeId) {
        Comment toSave = new Comment(UserService.getPrincipal(), recipeService.searchForRecipe(recipeId), comment.getComment());
        commentRepository.save(toSave);
        return new CommentDTO(toSave.getCommentOwner().getUsername(), comment.getComment(), toSave.getCreatedAt());
    }
}
