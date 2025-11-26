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
 * @author Sabien Ruijgrok, @Sybren Bonnema, @Pelle Meuzelaar
 * Populates database with eight recipes for demo purpose
 * and contains different users
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
        Category desert = makeCategory("Dessert");
        Category gezond = makeCategory("Gezond");
        Category snack = makeCategory("Snack");
        Category aziatisch = makeCategory("Aziatisch");

        Ingredient water = makeIngredient("Water", 0, 0, 0);
        Ingredient spekjes = makeIngredient("Spekjes", 1, 38, 15);     // average bacon bits
        Ingredient mosterd = makeIngredient("Mosterd", 5, 6, 5);
        Ingredient pasta = makeIngredient("Pasta", 75, 1, 13);
        Ingredient bacon = makeIngredient("Bacon", 1, 42, 13);
        Ingredient ei = makeIngredient("Ei", 1, 11, 13);
        Ingredient tomaten = makeIngredient("Tomaten", 3, 0, 1);
        Ingredient basilicum = makeIngredient("Basilicum", 3, 1, 3);
        Ingredient appel = makeIngredient("Appel", 14, 0, 0);
        Ingredient bloem = makeIngredient("Bloem", 76, 1, 10);
        Ingredient kaneel = makeIngredient("Kaneel", 81, 1, 4);
        Ingredient rijst = makeIngredient("Rijst", 78, 1, 7);
        Ingredient zalm = makeIngredient("Zalm", 0, 13, 20);
        Ingredient nori = makeIngredient("Nori", 5, 0, 5);
        Ingredient sla = makeIngredient("Sla", 2, 0, 1);
        Ingredient kip = makeIngredient("Kip", 0, 3, 27);
        Ingredient parmezaan = makeIngredient("Parmezaan", 4, 29, 35);
        Ingredient melk = makeIngredient("Melk", 5, 1, 3);
        Ingredient suiker = makeIngredient("Suiker", 100, 0, 0);

        makeRecipe("Mosterdsoep",
                "Een hartige soep met mosterd en spek",
                "/images/mosterdsoep 1.jpg",
                piet,
                List.of(diner, frans),
                Map.of(water, 500, spekjes, 100, mosterd, 50),
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
        recipeUserService.saveUser(user);

        return user;
    }

    private Category makeCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
        return category;
    }

    private Ingredient makeIngredient(String name, Integer carb, Integer fat, Integer protein) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(name);
        ingredient.setIngredientCarb(carb);
        ingredient.setIngredientFat(fat);
        ingredient.setIngredientProtein(protein);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

}
