package io.igorcossta.calories;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Calories {
    private String name;
    private double calories;
    private double servingSizeG;
    private double fatTotalG;
    private double fatSaturatedG;
    private double proteinG;
    private int sodiumMg;
    private int potassiumMg;
    private int cholesterolMg;
    private double carbohydratesTotalG;
    private double fiberG;
    private double sugarG;
}
