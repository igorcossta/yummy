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
public class RecipeEditDTO {
    private Long id;

    @NotEmpty(message = "{validation.messages.title.notEmpty}")
    @Size(min = 1, max = 60, message = "{validation.messages.title.size}")
    @Pattern(regexp = "^[A-Za-z\\s!#$%&()]{1,60}$", message = "{validation.messages.title.pattern}")
    private String title;

    @NotEmpty(message = "{validation.messages.description.notEmpty}")
    @Size(min = 1, max = 180, message = "{validation.messages.description.size}")
    private String description;

    @ListMustNotBeEmpty(message = "{validation.messages.ingredients.notEmpty}")
    private List<String> ingredients;

    @ListMustNotBeEmpty(message = "{validation.messages.howToPrepare.notEmpty}")
    private List<String> howToPrepare;

    @Positive(message = "{validation.messages.preparationTime.positive}")
    @Min(value = 1, message = "{validation.messages.preparationTime.min}")
    @Max(value = 999, message = "{validation.messages.preparationTime.max}")
    private int preparationTime = 1;

    @Positive(message = "{validation.messages.servings.positive}")
    @Min(value = 1, message = "{validation.messages.servings.min}")
    @Max(value = 99, message = "{validation.messages.servings.max}")
    private int servings = 1;
}
