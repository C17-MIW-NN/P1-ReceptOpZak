package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.CategoryRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * @author Sabien Ruijgrok
 * Handle request regarding Recipes
 */

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public RecipeController(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    // Show recipes
    @GetMapping({"/recipe/all", "/"})
    public String showRecipeOverview(Model datamodel, @RequestParam(required = false) Long categoryId) {

        if (categoryId != null) {
            datamodel.addAttribute("recipes",
                    recipeRepository.findByCategories_CategoryId(categoryId));
        } else {
            datamodel.addAttribute("recipes", recipeRepository.findAll());
        }

        datamodel.addAttribute("allCategories", categoryRepository.findAll());
        datamodel.addAttribute("selectedCategoryId", categoryId);


        return "recipeHome";
    }

    // Add recipes
    @GetMapping("/recipe/add")
    public String showRecipeForm(Model datamodel) {
        return showCategories(datamodel, new Recipe());
    }

    // Edit recipes
    @GetMapping("/recipe/edit/{recipeName}")
    public String showEditRecipeForm(@PathVariable("recipeName") String recipeName, Model datamodel) {
        Optional<Recipe> optionalRecipe = recipeRepository.findByRecipeName(recipeName);

        if (optionalRecipe.isPresent()) {
            return showCategories(datamodel, optionalRecipe.get());
        }

        return "redirect:/recipe/all";
    }

    private String showCategories(Model datamodel, Recipe recipe) {
        datamodel.addAttribute("formRecipe", recipe);
        datamodel.addAttribute("allCategories", categoryRepository.findAll());

        return "recipeForm";
    }

    // Detail page
    @GetMapping("/recipe/detail/{recipeName}")
    public String showRecipeDetailpage(@PathVariable("recipeName") String title, Model datamodel) {
        Optional<Recipe> recipeToShow = recipeRepository.findByRecipeName(title);


        if (recipeToShow.isEmpty()) {
            return "redirect:/recipe/all";
        }

        datamodel.addAttribute("recipe", recipeToShow.get());

        return "recipeDetail";

    }

    // Save recipes
    @PostMapping("/recipe/save")
    public String saveOrUpdateRecipe(@ModelAttribute("formRecipe") Recipe recipeSave,
                                     BindingResult result, Model datamodel) {

        if (result.hasErrors()) {
            return "redirect:/recipe/all";
        }

        Optional<Recipe> recipeWithSameTitle = recipeRepository.findByRecipeName(recipeSave.getRecipeName());

        if (recipeWithSameTitle.isPresent() && !recipeWithSameTitle.get().getRecipeId().equals(recipeSave.getRecipeId()
        )) {
            result.addError(new FieldError("recipe", "recipeName",
                    "this title is already used"));
        }

        if (result.hasErrors()) {
            return showCategories(datamodel, recipeSave);
        }

        recipeRepository.save(recipeSave);
        return "redirect:/recipe/detail/" + recipeSave.getRecipeName();
    }

    // Delete recipes
    @GetMapping("/recipe/delete/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        recipeRepository.deleteById(recipeId);
        return "redirect:/recipe/all";
    }


}
