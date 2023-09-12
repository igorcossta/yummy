package io.igorcossta.comment;

import io.igorcossta.recipe.Recipe;
import io.igorcossta.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

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
    private ZonedDateTime createdAt;

    public Comment(User commentOwner, Recipe recipe, String comment) {
        this.commentOwner = commentOwner;
        this.recipe = recipe;
        this.comment = comment;
        this.createdAt = ZonedDateTime.now(ZoneId.of("GMT"));
    }

    public static CommentViewDTO toCommentViewDTO(Comment comment) {
        return new CommentViewDTO(comment.getCommentOwner().getUsername(), comment.getComment(), comment.getCreatedAt());
    }
}
