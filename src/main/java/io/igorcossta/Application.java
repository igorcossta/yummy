package io.igorcossta;

import io.igorcossta.recipe.Recipe;
import io.igorcossta.recipe.RecipeRepository;
import io.igorcossta.user.Role;
import io.igorcossta.user.User;
import io.igorcossta.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, RecipeRepository recipeRepository) {
        return args -> {
            User user = userRepository.save(new User("a@a.com", "$2a$12$QqGryh9Vcmroc.GtIaPTCeoJSHJKMlulIJ3e1Kf7btUsQjk3twIaq", Role.USER));
            User user2 = userRepository.save(new User("b@b.com", "$2a$12$QqGryh9Vcmroc.GtIaPTCeoJSHJKMlulIJ3e1Kf7btUsQjk3twIaq", Role.ADMIN));

            for (int i = 0; i < 5; i++) {
                recipeRepository.save(new Recipe(user, "Food " + user.getUsername(), "Some quick example text to build on the card content.", "Flour 100g:Eggs 2 eggs:Milk 200ml:Salt 5g:Sugar 50g:Butter 50g", "Gather your ingredients:Prepare your workspace:Mix the dry ingredients:Add the wet ingredients:Combine the mixtures:Preheat the oven:Bake:Enjoy your delicious meal!", 40, 8));
            }
            for (int i = 0; i < 5; i++) {
                recipeRepository.save(new Recipe(user2, "Food " + user2.getUsername(), "Some quick example text to build on the card content.", "Flour 100g:Eggs 2 eggs:Milk 200ml:Salt 5g:Sugar 50g:Butter 50g", "Gather your ingredients:Prepare your workspace:Mix the dry ingredients:Add the wet ingredients:Combine the mixtures:Preheat the oven:Bake:Enjoy your delicious meal!", 40, 8));
            }
        };
    }
}
