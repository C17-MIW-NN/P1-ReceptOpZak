package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.User;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "userForm";
    }
}