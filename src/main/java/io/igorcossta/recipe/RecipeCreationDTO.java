package io.igorcossta.recipe;

import io.igorcossta.misc.ListMustNotBeEmpty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeCreationDTO {
    private Long id;

    @NotEmpty(message = "You must write a title before sending it")
    @Size(min = 1, max = 60, message = "Title must be between 1 and 60 characters")
    private String title;

    @NotEmpty(message = "You must write a description before sending it")
    @Size(min = 1, max = 180, message = "Description must be between 1 and 180 characters")
    private String description;

    @ListMustNotBeEmpty(message = "You must write all ingredients before sending it")
    private List<String> ingredients;

    @ListMustNotBeEmpty(message = "You must write all step-by-step before sending it")
    private List<String> howToPrepare;

    @PositiveOrZero(message = "Preparation time must be a positive number or zero")
    private int preparationTime;

    @PositiveOrZero(message = "Servings must be a positive number or zero")
    private int servings;
}
