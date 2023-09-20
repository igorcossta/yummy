package io.igorcossta.recipe;


import io.igorcossta.comment.Comment;
import io.igorcossta.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
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

    public static RecipeViewDTO toRecipeViewDTO(Recipe recipe) {
        return new RecipeViewDTO(recipe.getId(),
                recipe.getRecipeOwner().getUsername(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getIngredients(),
                recipe.getHowToPrepare(),
                recipe.getCreatedAt(),
                recipe.getPreparationTime(),
                recipe.getServings()
        );
    }

    public static Recipe fromRecipeCreationDTO(RecipeCreationDTO recipe, User owner) {
        String ingredients = String.join(":", recipe.getIngredients());
        String howToPrepare = String.join(":", recipe.getHowToPrepare());
        return new Recipe(owner,
                recipe.getTitle(),
                recipe.getDescription(),
                ingredients,
                howToPrepare,
                recipe.getPreparationTime(),
                recipe.getServings());
    }

    public static RecipeCreationDTO toRecipeCreationDTO(Recipe recipe) {
        List<String> ingredients = Arrays.asList(recipe.getIngredients().split(":"));
        List<String> howToPrepare = Arrays.asList(recipe.getHowToPrepare().split(":"));
        return new RecipeCreationDTO(recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                ingredients,
                howToPrepare,
                recipe.getPreparationTime(),
                recipe.getServings()
        );
    }
}
