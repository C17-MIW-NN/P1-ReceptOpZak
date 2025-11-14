package nl.mitwnn.ch17.ctrl_z.receptopzak;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Pelle Meuzelaar
 * Tests for ingredient class
 */

public class IngredientTest {
    @Test
    @DisplayName("test if the ingredients class calculates calories correctly")
    void testCalorieCalculation() {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientCarb(1);
        ingredient.setIngredientFat(1);
        ingredient.setIngredientProtein(1);
        ingredient.setIngredientKcal();

        Assertions.assertEquals(17, ingredient.getIngredientKcal());
    }
}
