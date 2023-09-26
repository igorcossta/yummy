package io.igorcossta.recipe;

import io.igorcossta.user.User;
import io.igorcossta.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RecipeMapper {
    @Mapping(target = "ingredients", expression = "java(listToString(dto.getIngredients()))")
    @Mapping(target = "howToPrepare", expression = "java(listToString(dto.getHowToPrepare()))")
    @Mapping(target = "recipeOwner", expression = "java(getCurrentUser())")
    @Mapping(target = "createdAt", expression = "java(java.time.ZonedDateTime.now())")
    abstract Recipe recipeCreationToEntity(RecipeCreationDTO dto);

    @Mapping(target = "ingredients", expression = "java(listToString(dto.getIngredients()))")
    @Mapping(target = "howToPrepare", expression = "java(listToString(dto.getHowToPrepare()))")
    abstract Recipe updateEntity(@MappingTarget Recipe entity, RecipeEditDTO dto);

    @Mapping(target = "ingredients", expression = "java(stringToList(recipe.getIngredients()))")
    @Mapping(target = "howToPrepare", expression = "java(stringToList(recipe.getHowToPrepare()))")
    abstract RecipeEditDTO entityToRecipeEditDto(Recipe recipe);

    @Mapping(target = "owner", source = "recipe.recipeOwner.username")
    abstract RecipeDetailsDTO entityToRecipeDetailsDto(Recipe recipe);

    abstract RecipeCardDTO entityToRecipeCardDto(Recipe recipe);

    protected String listToString(List<String> list) {
        return String.join(":", list);
    }

    protected List<String> stringToList(String str) {
        return Arrays.asList(str.split(":"));
    }

    protected User getCurrentUser() {
        return UserService.getPrincipal();
    }
}
