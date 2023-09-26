package io.igorcossta.recipe;


import io.igorcossta.comment.Comment;
import io.igorcossta.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recipeOwner;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> commentList;

    private String title;
    private String description;
    @Lob
    private String ingredients;
    @Lob
    private String howToPrepare;
    private ZonedDateTime createdAt;
    private int preparationTime;
    private int servings;
    private boolean isDisabled;

    public Recipe(User recipeOwner, String title, String description, String ingredients, String howToPrepare, int preparationTime, int servings) {
        this.recipeOwner = recipeOwner;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.howToPrepare = howToPrepare;
        this.createdAt = ZonedDateTime.now(ZoneId.of("GMT"));
        this.preparationTime = preparationTime;
        this.servings = servings;
        this.isDisabled = false;
    }
}
