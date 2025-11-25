package nl.mitwnn.ch17.ctrl_z.receptopzak.dto;

/**
 * @author Sabien Ruijgrok, @Pelle Meuzelaar
 * DTO represents the data required to register a new recipe user,
 * including username, password, and password confirmation
 */

public class NewRecipeUserDTO {

    private String username;
    private String password;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
