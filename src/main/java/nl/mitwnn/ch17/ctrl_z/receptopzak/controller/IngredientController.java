package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.IngredientRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Pelle Meuzelaar
 * Purpose for the class
 */

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public IngredientController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
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
    @GetMapping("/delete/{ingredientId}")
    public String deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);

        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();

            for (Recipe recipe : recipeRepository.findAll()) {
                recipe.getIngredients().remove(ingredient);
                recipeRepository.save(recipe);
            }

            ingredientRepository.deleteById(ingredientId);
        }
        return "redirect:/ingredient/all";
    }
}
