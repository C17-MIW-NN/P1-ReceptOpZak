package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.User;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sybren Bonnema
 * Purpose for the class
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public String showUserOverview(Model datamodel) {
        datamodel.addAttribute("allUsers", userRepository.findAll());
        datamodel.addAttribute("formUsers", new User());

        return "userOverview";
    }

    @GetMapping("/add")
    public String showUserForm(Model datamodel) {
        datamodel.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/user/all";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
       User user = userRepository.findById(id).orElseThrow();
       model.addAttribute("user", user);
       return "userForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/user/all";
    }
}