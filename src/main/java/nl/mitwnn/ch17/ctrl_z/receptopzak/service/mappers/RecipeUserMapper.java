package nl.mitwnn.ch17.ctrl_z.receptopzak.service.mappers;

import nl.mitwnn.ch17.ctrl_z.receptopzak.dto.NewRecipeUserDTO;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeUser;

/**
 * @author Sabien Ruijgrok
 * Doel van het project
 */
public class RecipeUserMapper {

    public static RecipeUser fromDTO(NewRecipeUserDTO newRecipeUserDTO) {
        RecipeUser recipeUser = new RecipeUser();

        recipeUser.setUserName(newRecipeUserDTO.getUsername());
        recipeUser.setPassword(newRecipeUserDTO.getPassword());

        return recipeUser;
    }


}
