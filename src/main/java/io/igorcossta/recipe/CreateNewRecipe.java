package io.igorcossta.recipe;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateNewRecipe {
    private String name;
    private String description;
    private String ingredients;
    private String howToPrepare;
}
