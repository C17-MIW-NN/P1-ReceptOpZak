package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeUser;
import nl.mitwnn.ch17.ctrl_z.receptopzak.service.RecipeUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

/**
 * @author Sabien Ruijgrok
 * Doel van het project
 */
@Controller
public class InitializerController {

    private final RecipeUserService recipeUserService;
    public InitializerController(RecipeUserService recipeUserService) {
        this.recipeUserService = recipeUserService;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        initializeDB();
    }

    private void initializeDB() {
        makeUser("Piet", "Pietje");

    }

    private RecipeUser makeUser(String username, String password) {
        RecipeUser user = new RecipeUser();

        user.setUserName(username);
        user.setPassword(password);

        recipeUserService.saveUser(user);
        return user;
    }

}

