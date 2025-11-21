package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Instruction;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeIngredient;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.*;
import nl.mitwnn.ch17.ctrl_z.receptopzak.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sabien Ruijgrok, @Pelle Meuzelaar, @Sybren Bonnema
 * Handle request regarding Recipes
 */

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeUserRepository recipeUserRepository;
    private final ImageService imageService;
    private final InstructionRepository instructionRepository;

    public RecipeController(RecipeRepository recipeRepository,
                            CategoryRepository categoryRepository,
                            IngredientRepository ingredientRepository,
                            RecipeUserRepository recipeUserRepository,
                            ImageService imageService,
                            InstructionRepository instructionRepository) {

        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeUserRepository = recipeUserRepository;
        this.imageService = imageService;
        this.instructionRepository = instructionRepository;
    }


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


    @GetMapping("/recipe/add")
    public String showRecipeForm(Model datamodel) {
        return showForm(datamodel, new Recipe());
    }


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
        datamodel.addAttribute("allUsers", recipeUserRepository.findAll());
        datamodel.addAttribute("allCategories", categoryRepository.findAll());
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        datamodel.addAttribute("formIngredient", new Ingredient());

        return "recipeForm";
    }

    @GetMapping("/recipe/detail/{recipeName}")
    public String showRecipeDetailpage(@PathVariable("recipeName") String title, Model datamodel) {

        Optional<Recipe> recipeToShow = recipeRepository.findByRecipeName(title);

        if (recipeToShow.isEmpty()) {
            return "redirect:/recipe/all";
        }

        datamodel.addAttribute("recipe", recipeToShow.get());

        return "recipeDetail";
    }

    @PostMapping("/recipe/save")
    public String saveOrUpdateRecipe(@ModelAttribute("formRecipe") Recipe recipeSave,
                                     @RequestParam(value = "ingredientNames[]", required = false)
                                     List<String> ingredientNames,
                                     @RequestParam(value = "quantities[]", required = false)
                                     List<Integer> quantities,
                                     @RequestParam(value = "instructionTexts", required = false)
                                     List<String> instructionTexts,
                                     BindingResult result, Model datamodel,
                                     @RequestParam(value = "recipeImage", required = false)
                                     MultipartFile recipeImage) {

        Recipe originalRecipe = checkImage(recipeSave);

        saveRecipeImage(recipeSave, originalRecipe, result, recipeImage);

        if (result.hasErrors()) {
            return "redirect:/recipe/all";
        }

        String validationResult = validateRecipeTitle(recipeSave, result, datamodel);
        if (validationResult != null) return validationResult;

        ingredientSave(recipeSave, ingredientNames, quantities);
        instructionRepository.deleteAll(recipeSave.getInstructions());

        List<Instruction> instructions = getInstructionList(recipeSave, instructionTexts);

        instructionRepository.saveAll(instructions);
        recipeSave.getInstructions().clear();
        recipeSave.getInstructions().addAll(instructions);
        recipeRepository.save(recipeSave);

        return "redirect:/recipe/detail/" + recipeSave.getRecipeName();
    }

    private static List<Instruction> getInstructionList(Recipe recipeSave, List<String> instructionTexts) {
        List<Instruction> instructions = new ArrayList<>();

        if (instructionTexts != null) {
            for (int i = 0; i < instructionTexts.size(); i++) {
                String text = instructionTexts.get(i);
                if (!text.isBlank()) {
                    Instruction instruction = new Instruction();
                    instruction.setStepNumber(i + 1);
                    instruction.setText(text);
                    instruction.setRecipe(recipeSave);
                    instructions.add(instruction);
                }
            }
        }
        return instructions;
    }

    private void ingredientSave(Recipe recipe, List<String> names, List<Integer> quantities) {
        names = names == null ? new ArrayList<>() : names;
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            int quantity = (i < quantities.size()) ? quantities.get(i) : 0;

            Ingredient ingredient = ingredientRepository
                    .findByIngredientName(name)
                    .orElseGet(() -> ingredientRepository.save(new Ingredient(name)));

            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setQuantity(quantity);
            recipeIngredient.setRecipe(recipe);
            recipeIngredients.add(recipeIngredient);
        }

        recipe.setRecipeIngredients(recipeIngredients);
        recipeRepository.save(recipe);
    }


    private Recipe checkImage(Recipe recipeSave) {
        Recipe originalRecipe = null;
        if (recipeSave.getRecipeId() != null) {
            originalRecipe = recipeRepository.findById(recipeSave.getRecipeId())
                    .orElse(null);
        }
        return originalRecipe;
    }

    private void saveRecipeImage(Recipe recipeSave,
                                 Recipe originalRecipe,
                                 BindingResult result,
                                 MultipartFile recipeImage) {
        try {
            if (recipeImage != null && !recipeImage.isEmpty()) {
                imageService.saveImage(recipeImage);
                recipeSave.setImageURL("/image/" + recipeImage.getOriginalFilename());
            } else {
                if (originalRecipe == null || originalRecipe.getImageURL() == null) {
                    recipeSave.setImageURL("/images/defaultRecipe.png");
                } else {
                    recipeSave.setImageURL(originalRecipe.getImageURL());
                }
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

    @GetMapping("/recipe/delete/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        recipeRepository.deleteById(recipeId);
        return "redirect:/recipe/all";
    }
}
