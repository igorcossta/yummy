package io.igorcossta.recipe;


import io.igorcossta.comment.Comment;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeAndComments {
    private Recipe recipe;
    private List<Comment> comments = new ArrayList<>();
}
