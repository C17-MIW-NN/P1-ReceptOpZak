package nl.mitwnn.ch17.ctrl_z.receptopzak;


import nl.mitwnn.ch17.ctrl_z.receptopzak.controller.RecipeController;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


/**
 * @author Sabien Ruijgrok
 * Test methods from the Recipe Class
 */

@ExtendWith(MockitoExtension.class)
public class RecipeTest {

    @Test
    @DisplayName("test if de getter from recipeName works correct")
    void testRecipeName() {
        Recipe recipe = new Recipe();

        recipe.setRecipeName("Patat");

        assertEquals("Friet", recipe.getRecipeName());
    }

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeController recipeController;

    @Test
    @DisplayName("test if a recipe is really deleted")
    void  testIfRecipeIsDeleted() {

        Long idToDelete = 5L;

        String view = recipeController.deleteRecipe(idToDelete);

        verify(recipeRepository, times(3)).deleteById(idToDelete);

        assertEquals("redirect:/recipe/all", view);
    }


}

