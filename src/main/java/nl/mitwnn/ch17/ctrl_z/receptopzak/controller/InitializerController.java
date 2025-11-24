package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.*;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.CategoryRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.IngredientRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.service.RecipeUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * @authors Sabien Ruijgrok & Sybren Bonnema
 * Populates database with eight recipes for demo purposes
 */

@Controller
public class InitializerController {

    private final RecipeUserService recipeUserService;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;

    public InitializerController(RecipeUserService recipeUserService,
                                 RecipeRepository recipeRepository,
                                 CategoryRepository categoryRepository,
                                 IngredientRepository ingredientRepository) {
        this.recipeUserService = recipeUserService;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if (recipeRepository.count() == 0) {
            initializeDB();
        }
    }

    private void initializeDB() {
        RecipeUser admin = makeUser("Admin", "Admin");
        RecipeUser piet = makeUser("Piet", "Pietje");
        RecipeUser klaas = makeUser("Klaas", "Klaasje");
        RecipeUser kees = makeUser("Kees", "Keesje");
        RecipeUser anouk = makeUser("Anouk", "Anoukje");

        Category ontbijt = makeCategory("Ontbijt");
        Category diner = makeCategory("Diner");
        Category frans = makeCategory("Frans");
        Category lunch = makeCategory("Lunch");
        Category desert = makeCategory("Desert");
        Category gezond = makeCategory("Gezond");
        Category snack = makeCategory("Snack");
        Category aziatisch = makeCategory("Aziatisch");

        Ingredient water = makeIngredient("Water");
        Ingredient spekjes = makeIngredient("Spekjes");
        Ingredient mosterd = makeIngredient("Mosterd");
        Ingredient pasta = makeIngredient("Pasta");
        Ingredient bacon = makeIngredient("Bacon");
        Ingredient ei = makeIngredient("Ei");
        Ingredient tomaten = makeIngredient("Tomaten");
        Ingredient basilicum = makeIngredient("Basilicum");
        Ingredient appel = makeIngredient("Appel");
        Ingredient bloem = makeIngredient("Bloem");
        Ingredient kaneel = makeIngredient("Kaneel");
        Ingredient rijst = makeIngredient("Rijst");
        Ingredient zalm = makeIngredient("Zalm");
        Ingredient nori = makeIngredient("Nori");
        Ingredient sla = makeIngredient("Sla");
        Ingredient kip = makeIngredient("Kip");
        Ingredient parmezaan = makeIngredient("Parmezaan");
        Ingredient melk = makeIngredient("Melk");
        Ingredient suiker = makeIngredient("Suiker");

        makeRecipe("Mosterdsoep",
                "Een hartige soep met mosterd en spek",
                "/images/mosterdsoep 1.jpg",
                piet,
                List.of(diner, frans),
                Map.of(water, 5, spekjes, 3, mosterd, 1),
                List.of("Breng water aan de kook",
                        "voeg de mosterd toe", "voeg de spekjes toe"));

        makeRecipe("Pasta Carbonara",
                "Een klassieke Italiaanse pasta met spek en ei",
                "/images/carbonara 1.jpg",
                piet,
                List.of(diner, frans),
                Map.of(pasta, 200, bacon, 100, ei, 2),
                List.of("Kook de pasta",
                        "Bak de bacon",
                        "Meng pasta met bacon en ei"));

        makeRecipe("Tomatensoep",
                "Klassieke Hollandse tomatensoep met verse basilicum",
                "/images/tomatensoep.jpg",
                piet,
                List.of(diner, gezond),
                Map.of(water, 500, tomaten, 300, basilicum, 20),
                List.of("Breng water aan de kook",
                        "Voeg tomaten toe",
                        "Garneer met basilicum"));

        makeRecipe("Appeltaart",
                "Traditionele Nederlandse appeltaart met kaneel",
                "/images/appeltaart.jpg",
                klaas,
                List.of(desert, snack),
                Map.of(appel, 3, bloem, 200, kaneel, 10),
                List.of("Snijd appels in stukjes",
                        "Meng met bloem en kaneel",
                        "Bak in de oven"));


        makeRecipe("Sushi Maki",
                "Japanse rolletjes met rijst en zalm",
                "/images/sushi.jpg",
                piet,
                List.of(lunch, aziatisch),
                Map.of(rijst, 200, zalm, 100, nori, 3),
                List.of("Kook de rijst",
                        "Leg rijst en zalm op nori",
                        "Rol strak op"));


        makeRecipe("Caesar Salad",
                "Frisse salade met kip en Parmezaanse kaas",
                "/images/caesar.jpg",
                admin,
                List.of(lunch, gezond),
                Map.of(sla, 100, kip, 150, parmezaan, 30),
                List.of("Snijd kip in stukjes",
                        "Meng kip met sla",
                        "Strooi Parmezaan erover"));


        makeRecipe("Crêpes",
                "Dunne Franse pannenkoekjes met suiker",
                "/images/crepes.jpg",
                piet,
                List.of(ontbijt, frans),
                Map.of(bloem, 150, melk, 200, suiker, 30),
                List.of("Meng bloem, melk en suiker",
                        "Bak dunne pannenkoekjes",
                        "Serveer warm"));

    }

    private Recipe makeRecipe(String recipeName, String description, String imageURL,
                              RecipeUser recipeUser, List<Category> categories,
                              Map<Ingredient, Integer> ingredientsWithQuantities,
                              List<String> instructionTexts) {

        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setDescription(description);
        recipe.setImageURL(imageURL);
        recipe.setRecipeUser(recipeUser);
        recipe.setCategories(new HashSet<>(categories));

        List<Instruction> instructions = new ArrayList<>();
        int stepNumber = 1;
        for (String text : instructionTexts) {
            Instruction instruction = new Instruction();
            instruction.setStepNumber(stepNumber++);
            instruction.setText(text);
            instruction.setRecipe(recipe);
            instructions.add(instruction);
        }
        recipe.setInstructions(instructions);

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        for (Map.Entry<Ingredient, Integer> entry : ingredientsWithQuantities.entrySet()) {
            RecipeIngredient ri = new RecipeIngredient();
            ri.setIngredient(entry.getKey());
            ri.setQuantity(entry.getValue());
            ri.setRecipe(recipe);
            recipeIngredients.add(ri);
        }
        recipe.setRecipeIngredients(recipeIngredients);

        recipeRepository.save(recipe);
        return recipe;
    }

    private RecipeUser makeUser(String username, String rawPassword) {
        RecipeUser user = new RecipeUser();

        user.setUserName(username);
        user.setPassword(rawPassword);

        if (username.equals("Admin")) {
            user.setRole("ROLE_ADMIN");
        } else {
            user.setRole("ROLE_USER");
        }

        user.setPassword(rawPassword);
        return user;
    }

    private Category makeCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
        return category;
    }

    private Ingredient makeIngredient(String ingredientName) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientName);
        ingredientRepository.save(ingredient);
        return ingredient;
    }
}
