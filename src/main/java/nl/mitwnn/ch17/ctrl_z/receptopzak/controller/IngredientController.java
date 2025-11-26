package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import jakarta.transaction.Transactional;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.IngredientRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeIngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * @author Pelle Meuzelaar
 * Handles requests regarding ingredients
 */

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public IngredientController(IngredientRepository ingredientRepository,
                                RecipeIngredientRepository recipeIngredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @GetMapping("/all")
    public String showIngredientOverview(Model datamodel) {
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        datamodel.addAttribute("formIngredients", new Ingredient());

        return "ingredientOverview";
    }

    @Transactional
    @GetMapping("/delete/{ingredientId}")
    public String deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {

        recipeIngredientRepository.deleteAllByIngredient_IngredientId(ingredientId);

        ingredientRepository.deleteById(ingredientId);

        return "redirect:/ingredient/all";
    }
}
