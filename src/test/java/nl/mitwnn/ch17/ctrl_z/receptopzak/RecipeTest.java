package nl.mitwnn.ch17.ctrl_z.receptopzak;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sabien Ruijgrok
 * Specifically tests the getter and setter for the recipe name and testing favorite recipes
 */

@ExtendWith(MockitoExtension.class)
public class RecipeTest {

    @Test
    @DisplayName("test if de getter from recipeName works correct")
    void testRecipeName() {
        Recipe recipe = new Recipe();

        recipe.setRecipeName("Friet");

        assertEquals("Friet", recipe.getRecipeName());
    }

    @Test
    @DisplayName("count favorite recipes when all are favorite")
    void testAllFavoriteRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Recipe recipe = new Recipe();
            recipe.setFavorite(true);
            recipes.add(recipe);
        }

        int favoriteCount = 0;
        for (Recipe recipe : recipes) {
            if (recipe.isFavorite()) {
                favoriteCount++;
            }
        }

        assertEquals(3, favoriteCount);
    }

    @Test
    @DisplayName("count favorite recipes when some are favorite")
    void testSomeFavoriteRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        Recipe spaghetti = new Recipe();
        spaghetti.setFavorite(true);

        Recipe lasagne = new Recipe();
        lasagne.setFavorite(false);

        Recipe soep = new Recipe();
        soep.setFavorite(true);

        recipes.add(spaghetti);
        recipes.add(lasagne);
        recipes.add(soep);

        int favoriteCount = 0;
        for (Recipe recipe : recipes) {
            if (recipe.isFavorite()) {
                favoriteCount++;
            }
        }

        assertEquals(2, favoriteCount);
    }

}

