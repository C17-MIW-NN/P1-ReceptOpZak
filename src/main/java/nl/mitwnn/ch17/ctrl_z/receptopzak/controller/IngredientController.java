package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Category;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



/**
 * @author Pelle Meuzelaar
 * Purpose for the class
 */

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
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
            return "redirect:/ingredients/all";
        }

        ingredientRepository.save(ingredient);
        return "redirect:/ingredients/all";
    }

    // Delete ingredients
    @GetMapping("/delete/{ingredientId}")
    public String deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
        return "redirect:/ingredient/all";
    }
}
