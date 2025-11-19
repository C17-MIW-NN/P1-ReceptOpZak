package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.dto.NewRecipeUserDTO;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeUser;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeUserRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.service.RecipeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sybren Bonnema
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
        datamodel.addAttribute("formUsers", new RecipeUser());

        return "userOverview";
    }

    @GetMapping("/add")
    public String showUserForm(Model datamodel) {
        datamodel.addAttribute("user", new RecipeUser());
        return "userForm";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("formUsers") NewRecipeUserDTO userDtoToBeSaved, BindingResult result, Model datamodel) {

        if (recipeUserService.usernameInUse(userDtoToBeSaved.getUsername())) {
            result.rejectValue("username", "duplicate", "Username is already in use");
        }

        if (!userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getPassword())) {
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


    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
       RecipeUser recipeUser = recipeUserRepository.findById(id).orElseThrow();
       model.addAttribute("user", recipeUser);
       return "userForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        recipeUserRepository.deleteById(id);
        return "redirect:/recipeUser/all";
    }

}