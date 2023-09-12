package io.igorcossta.recipe;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeCreationDTO {
    private String title;
    private String description;
    private List<String> ingredients;
    private List<String> howToPrepare;
    private int preparationTime;
    private int servings;
}
