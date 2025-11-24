package nl.mitwnn.ch17.ctrl_z.receptopzak;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Category;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sabien Ruijgrok
 * Unit Tests for counting favorite categories in a Recipe.
 */

public class CategoryTest {

    @Test
    @DisplayName("test number of favorite categories when all are favorite")
    void testNumberOfFavoriteCategoriesWhenAll() {
        Recipe recipe = new Recipe();
        recipe.setCategories(new HashSet<>());

        for (int i = 0; i < 3; i++) {
            Category category = new Category();
            category.setFavorite(true);
            recipe.getCategories().add(category);
        }

        int result = recipe.countFavoriteCategories();

        assertEquals(3, result);
    }

    @Test
    @DisplayName("test number of favorite categories when some are favorite")
    void testNumberOfFavoriteCategoriesWhenSome() {
        Recipe recipe = new Recipe();
        recipe.setCategories(new HashSet<>());

        Category hard = new Category();
        hard.setFavorite(true);

        Category vegan = new Category();
        vegan.setFavorite(false);

        Category fastfood = new Category();
        fastfood.setFavorite(true);

        recipe.getCategories().add(hard);
        recipe.getCategories().add(vegan);
        recipe.getCategories().add(fastfood);

        int result = recipe.countFavoriteCategories();

        assertEquals(2, result);
    }

}
