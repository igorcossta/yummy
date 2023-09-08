package io.igorcossta.recipe;

import io.igorcossta.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByRecipeOwner(User owner);
}
