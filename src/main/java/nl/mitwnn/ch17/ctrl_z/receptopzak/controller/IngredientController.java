package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import jakarta.transaction.Transactional;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.IngredientRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeIngredientRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Pelle Meuzelaar
 * Handles requests regarding ingredients
 */

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public IngredientController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    // Show ingredients
    @GetMapping("/all")
    public String showIngredientOverview(Model datamodel) {
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        datamodel.addAttribute("formIngredients", new Ingredient());

        return "ingredientOverview";
    }

    // Save ingredients
    @PostMapping("/save")
    public String saveOrUpdateIngredient(@ModelAttribute("formIngredient") Ingredient ingredient, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/ingredient/all";
        }

        ingredient.setIngredientKcal();

        ingredientRepository.save(ingredient);
        return "redirect:/ingredient/all";
    }

    // Delete ingredients
    @Transactional
    @GetMapping("/delete/{ingredientId}")
    public String deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {

        recipeIngredientRepository.deleteAllByIngredient_IngredientId(ingredientId);

        ingredientRepository.deleteById(ingredientId);

        return "redirect:/ingredient/all";
    }
}
