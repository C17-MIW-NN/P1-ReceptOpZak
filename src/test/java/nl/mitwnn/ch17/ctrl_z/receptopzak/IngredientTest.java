package nl.mitwnn.ch17.ctrl_z.receptopzak;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Pelle Meuzelaar
 * Unit tests for Ingredient class
 */

public class IngredientTest {
    @Test
    @DisplayName("test if the ingredients class calculates calories correctly when using normal integer input")
    void testCalorieCalculationNormal() {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientCarb(1);
        ingredient.setIngredientFat(1);
        ingredient.setIngredientProtein(1);
        ingredient.calculateIngredientKcalPerDefaultQuantity();

        Assertions.assertEquals(17, ingredient.calculateIngredientKcalPerDefaultQuantity());
    }

    @Test
    @DisplayName("test if the ingredients class calculates calories correctly when using null input")
    void testCalorieCalculationNullValues() {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientCarb(null);
        ingredient.setIngredientFat(1);
        ingredient.setIngredientProtein(null);
        ingredient.calculateIngredientKcalPerDefaultQuantity();

        Assertions.assertEquals(9, ingredient.calculateIngredientKcalPerDefaultQuantity());
    }

    @Test
    @DisplayName("test if the ingredients class calculates calories correctly when using 0 as input")
    void testCalorieCalculationZeroValues() {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientCarb(1);
        ingredient.setIngredientFat(0);
        ingredient.setIngredientProtein(1);
        ingredient.calculateIngredientKcalPerDefaultQuantity();

        Assertions.assertEquals(8, ingredient.calculateIngredientKcalPerDefaultQuantity());
    }

}
