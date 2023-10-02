package io.igorcossta.recipe;

import io.igorcossta.misc.ListMustNotBeEmpty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeCreationDTO {
    @NotEmpty(message = "You must write a title before sending it")
    @Size(min = 1, max = 60, message = "Title must be between 1 and 60 characters")
    @Pattern(regexp = "^[A-Za-z\\s!#$%&()]{1,60}$", message = "Title can only consist of letters (A-Z, a-z) and certain special characters (!, #, $, %, &, and ())")
    private String title;

    @NotEmpty(message = "You must write a description before sending it")
    @Size(min = 1, max = 180, message = "Description must be between 1 and 180 characters")
    private String description;

    @ListMustNotBeEmpty(message = "You must write all ingredients before sending it")
    private List<String> ingredients;

    @ListMustNotBeEmpty(message = "You must write all step-by-step before sending it")
    private List<String> howToPrepare;

    @Positive(message = "Time to prepare must be a positive number")
    @Min(value = 1, message = "Time to prepare should be at least 1 minute")
    @Max(value = 999, message = "Time to prepare cannot exceed 999 minutes")
    private int preparationTime = 1;

    @Positive(message = "Number of servings must be a positive number")
    @Min(value = 1, message = "Number of servings should be at least 1")
    @Max(value = 99, message = "Number of servings cannot exceed 99")
    private int servings = 1;
}
