package nl.mitwnn.ch17.ctrl_z.receptopzak;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sybren Bonnema
 * Testing elements of the User class
 */

public class UserTest {

    @Test
    @DisplayName("test if the username getter works correctly")
    void testUsername() {
        RecipeUser user = new RecipeUser();

        user.setUserName("Piet Patat");

        assertEquals("Frans Friet", user.getUserName());
    }

    @Test
    @DisplayName("Test if the default recipe list is empty")
    void testDefaultRecipeListIsEmpty() {
        RecipeUser user = new RecipeUser();
        assertNotNull(user.getRecipes());
        assertTrue(user.getRecipes().isEmpty());

    }
}
