package io.igorcossta.calories;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CaloriesService {
    private final WebClient client;

    public Mono<Calories> getCaloriesFor(String query) {
        String str = query.replace(":", " ");
        return client
                .get()
                .uri("?query=" + str)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(CaloriesResponse.class)
                .map(this::sumCaloriesFor);
    }

    private Calories sumCaloriesFor(CaloriesResponse calories) {
        return calories.getItems().stream()
                .reduce(new Calories(), (totalCalories, caloriesItem) -> {
                    totalCalories.setCalories(totalCalories.getCalories() + caloriesItem.getCalories());
                    totalCalories.setServingSizeG(totalCalories.getServingSizeG() + caloriesItem.getServingSizeG());
                    totalCalories.setFatTotalG(totalCalories.getFatTotalG() + caloriesItem.getFatTotalG());
                    totalCalories.setFatSaturatedG(totalCalories.getFatSaturatedG() + caloriesItem.getFatSaturatedG());
                    totalCalories.setProteinG(totalCalories.getProteinG() + caloriesItem.getProteinG());
                    totalCalories.setSodiumMg(totalCalories.getSodiumMg() + caloriesItem.getSodiumMg());
                    totalCalories.setPotassiumMg(totalCalories.getPotassiumMg() + caloriesItem.getPotassiumMg());
                    totalCalories.setCholesterolMg(totalCalories.getCholesterolMg() + caloriesItem.getCholesterolMg());
                    totalCalories.setCarbohydratesTotalG(totalCalories.getCarbohydratesTotalG() + caloriesItem.getCarbohydratesTotalG());
                    totalCalories.setFiberG(totalCalories.getFiberG() + caloriesItem.getFiberG());
                    totalCalories.setSugarG(totalCalories.getSugarG() + caloriesItem.getSugarG());
                    return totalCalories;
                });
    }

}
