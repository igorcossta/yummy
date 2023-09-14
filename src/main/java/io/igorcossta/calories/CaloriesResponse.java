package io.igorcossta.calories;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CaloriesResponse {
    private List<Calories> items;
}
