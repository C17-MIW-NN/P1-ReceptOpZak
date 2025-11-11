package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sybren Bonnema
 * Define characterists for the Author class
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    String userName;

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes = new ArrayList<>();


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
