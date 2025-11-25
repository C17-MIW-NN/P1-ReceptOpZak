package nl.mitwnn.ch17.ctrl_z.receptopzak;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeIngredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Pelle Meuzelaar
 * Unit tests for RecipeIngredient class
 */

public class RecipeIngredientTest {
    @Test
    @DisplayName("test if the RecipeIngredient class calculates calories correctly when using normal integer input")
    void testCalculateKcalIngredientPerServingNormal() {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientCarb(25);
        ingredient.setIngredientFat(10);
        ingredient.setIngredientProtein(25);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setQuantity(10);

        Assertions.assertEquals(29, recipeIngredient.calculateKcalIngredientPerServing(), 0.01);
    }

    @Test
    @DisplayName("test if the RecipeIngredient class calculates calories correctly when using normal null input")
    void testCalculateKcalIngredientPerServingNullValues() {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientCarb(null);
        ingredient.setIngredientFat(null);
        ingredient.setIngredientProtein(null);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setQuantity(200);

        Assertions.assertEquals(0, recipeIngredient.calculateKcalIngredientPerServing());
    }

    @Test
    @DisplayName("test if the RecipeIngredient class calculates calories correctly when using 0 as input")
    void testCalculateKcalIngredientPerServingZeroValues() {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientCarb(0);
        ingredient.setIngredientFat(0);
        ingredient.setIngredientProtein(1);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setQuantity(100);

        Assertions.assertEquals(4, recipeIngredient.calculateKcalIngredientPerServing());
    }

}
