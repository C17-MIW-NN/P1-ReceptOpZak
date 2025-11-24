package nl.mitwnn.ch17.ctrl_z.receptopzak;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sabien Ruijgrok
 * Specifically tests the getter and setter for the recipe name.
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

}

