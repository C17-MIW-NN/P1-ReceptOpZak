package nl.mitwnn.ch17.ctrl_z.receptopzak;


import nl.mitwnn.ch17.ctrl_z.receptopzak.controller.RecipeController;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Category;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sabien Ruijgrok
 * Test methods from the Recipe Class
 */


public class RecipeTest {

    @Test
    @DisplayName("test if de getter from recipeName works correct")
    void testRecipeName() {
        Recipe recipe = new Recipe();

        recipe.setRecipeName("Patat");

        assertEquals("Friet", recipe.getRecipeName());

    }

}
