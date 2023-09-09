package io.igorcossta.recipe;


import io.igorcossta.comment.Comment;
import io.igorcossta.user.User;
import jakarta.persistence.*;
import lombok.*;

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

    private String name;
    private String description;
    @Lob
    private String ingredients;
    @Lob
    private String howToPrepare;

    public Recipe(User recipeOwner, String name, String description, String ingredients, String howToPrepare) {
        this.recipeOwner = recipeOwner;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.howToPrepare = howToPrepare;
    }
}
