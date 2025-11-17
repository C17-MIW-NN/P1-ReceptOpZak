package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.CategoryRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.IngredientRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.UserRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author Sabien Ruijgrok
 * Handle request regarding Recipes
 */

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public RecipeController(RecipeRepository recipeRepository, CategoryRepository categoryRepository, IngredientRepository ingredientRepository, UserRepository userRepository, ImageService imageService) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
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
        return showForm(datamodel, new Recipe());
    }

    // Edit recipes
    @GetMapping("/recipe/edit/{recipeName}")
    public String showEditRecipeForm(@PathVariable("recipeName") String recipeName, Model datamodel) {
        Optional<Recipe> optionalRecipe = recipeRepository.findByRecipeName(recipeName);

        if (optionalRecipe.isPresent()) {
            return showForm(datamodel, optionalRecipe.get());
        }

        return "redirect:/recipe/all";
    }

    private String showForm(Model datamodel, Recipe recipe) {
        datamodel.addAttribute("formRecipe", recipe);
        datamodel.addAttribute("allUsers", userRepository.findAll());
        datamodel.addAttribute("allCategories", categoryRepository.findAll());
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        datamodel.addAttribute("formIngredient", new Ingredient());

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
                                     BindingResult result, Model datamodel,
                                     @RequestParam(value = "recipeImage", required = false) MultipartFile recipeImage) {

        saveRecipeImage(recipeSave, result, recipeImage);

        if (result.hasErrors()) {
            return "redirect:/recipe/all";
        }

        String validationResult = validateRecipeTitle(recipeSave, result, datamodel);
        if (validationResult != null) return validationResult;

        Ingredient newIngredient = recipeSave.getNewIngredient();

        boolean hasNewIngredient = newIngredient != null && newIngredient.getIngredientName() != null &&
                !newIngredient.getIngredientName().isBlank();

        if (hasNewIngredient) {
            newIngredient.setIngredientKcal();
            Ingredient savedIngredient = ingredientRepository.save(newIngredient);

            if (recipeSave.getIngredients() == null) {
                recipeSave.setIngredients(new HashSet<>());
            }

            recipeSave.getIngredients().add(savedIngredient);
        }

        recipeRepository.save(recipeSave);
        return "redirect:/recipe/detail/" + recipeSave.getRecipeName();
    }

    private void saveRecipeImage(Recipe recipeSave, BindingResult result, MultipartFile recipeImage) {
        try {
            if (recipeImage != null && !recipeImage.isEmpty()) {
                imageService.saveImage(recipeImage);
                recipeSave.setImageURL("/image/" + recipeImage.getOriginalFilename());
            } else {
                recipeSave.setImageURL("/images/defaultRecipe.png");
            }
        } catch (IOException imageError) {
            result.rejectValue("recipeImage", "imageNotSaved", "Image not saved");
        }
    }

    private String validateRecipeTitle(Recipe recipeSave, BindingResult result, Model datamodel) {
        Optional<Recipe> recipeWithSameTitle = recipeRepository.findByRecipeName(recipeSave.getRecipeName());

        if (recipeWithSameTitle.isPresent() && !recipeWithSameTitle.get().getRecipeId().equals(recipeSave.getRecipeId()
        )) {
            result.addError(new FieldError("formRecipe", "recipeNaam",
                    "this title is already used"));
        }

        if (result.hasErrors()) {
            return showForm(datamodel, recipeSave);
        }
        return null;
    }

    // Delete recipes
    @GetMapping("/recipe/delete/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        recipeRepository.deleteById(recipeId);
        return "redirect:/recipe/all";
    }


}