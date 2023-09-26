package io.igorcossta.recipe;

import io.igorcossta.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByRecipeOwner(User owner);

    @Modifying
    @Query("UPDATE recipe r SET r.isDisabled = true WHERE r.id = :id")
    void disableRecipeById(Long id);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM recipe r WHERE r.id = :id AND r.isDisabled = true")
    boolean isRecipeDisabled(Long id);

    @Query("""
            SELECT new io.igorcossta.recipe.RecipeCardDTO(r.id, r.title, r.description, r.createdAt) 
            FROM recipe r 
            WHERE r.recipeOwner = :owner 
            AND r.isDisabled = false
            """)
    List<RecipeCardDTO> findAllActiveRecipesByOwner(User owner);

    @Query("""
            SELECT new io.igorcossta.recipe.RecipeCardDTO(r.id, r.title, r.description, r.createdAt) 
            FROM recipe r 
            WHERE r.isDisabled = false
            """)
    List<RecipeCardDTO> findAllActiveRecipes();
}
