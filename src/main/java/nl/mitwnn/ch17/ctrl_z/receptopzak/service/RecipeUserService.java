package nl.mitwnn.ch17.ctrl_z.receptopzak.service;

import nl.mitwnn.ch17.ctrl_z.receptopzak.dto.NewRecipeUserDTO;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeUser;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.RecipeUserRepository;
import nl.mitwnn.ch17.ctrl_z.receptopzak.service.mappers.RecipeUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @author Sabien Ruijgrok
 */

@Service
public class RecipeUserService implements UserDetailsService {

    private final RecipeUserRepository recipeUserRepository;
    private final PasswordEncoder passwordEncoder;

    public RecipeUserService(RecipeUserRepository recipeUserRepository, PasswordEncoder passwordEncoder) {
        this.recipeUserRepository = recipeUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return recipeUserRepository.findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User " + username + " was not found."));
    }

    public void saveUser(RecipeUser recipeUser) {
        recipeUser.setPassword(passwordEncoder.encode(recipeUser.getPassword()));
        recipeUserRepository.save(recipeUser);
    }

    public List<RecipeUser> getAllUsers() {
        return recipeUserRepository.findAll();
    }

    public boolean usernameInUse(String username) {
        return recipeUserRepository.existsByUserName(username);
    }

    public void save(NewRecipeUserDTO userDtoToBeSaved) {
        saveUser(RecipeUserMapper.fromDTO(userDtoToBeSaved));
    }

}
