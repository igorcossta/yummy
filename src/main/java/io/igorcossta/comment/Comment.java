package io.igorcossta.comment;

import io.igorcossta.recipe.Recipe;
import io.igorcossta.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentOwner;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Lob
    private String comment;

    public Comment(User commentOwner, Recipe recipe, String comment) {
        this.commentOwner = commentOwner;
        this.recipe = recipe;
        this.comment = comment;
    }
}
