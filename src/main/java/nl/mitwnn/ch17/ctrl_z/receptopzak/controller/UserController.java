package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.dto.NewRecipeUserDTO;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeUserRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.service.RecipeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sybren Bonnema, @Pelle Meuzelaar, @Sabien Ruijgrok
 * Helps the program store and display users
 */

@Controller
@RequestMapping("/recipeUser")
public class UserController {

    private final RecipeUserRepository recipeUserRepository;
    private final RecipeUserService recipeUserService;

    public UserController(RecipeUserRepository recipeUserRepository, RecipeUserService recipeUserService) {
        this.recipeUserRepository = recipeUserRepository;
        this.recipeUserService = recipeUserService;
    }

    @GetMapping("/all")
    public String showUserOverview(Model datamodel) {
        datamodel.addAttribute("allUsers", recipeUserRepository.findAll());
        datamodel.addAttribute("formUser", new NewRecipeUserDTO());
        datamodel.addAttribute("formModalHidden", true);

        return "userOverview";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("formUser") NewRecipeUserDTO userDtoToBeSaved,
                           BindingResult result, Model datamodel) {

        if (recipeUserService.usernameInUse(userDtoToBeSaved.getUsername())) {
            result.rejectValue("username", "duplicate", "Username is already in use");
        }

        if (!userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getConfirmPassword())) {
            result.rejectValue("password", "no.match", "Passwords do not match");
        }

        if (result.hasErrors()) {
            datamodel.addAttribute("allUsers", recipeUserService.getAllUsers());
            datamodel.addAttribute("formModalHidden", false);
            return "userOverview";
        }

        recipeUserService.save(userDtoToBeSaved);
        return "redirect:/recipeUser/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        recipeUserRepository.deleteById(id);
        return "redirect:/recipeUser/all";
    }
}