package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Sabien Ruijgrok
 * This is the recipe entity (later more description)
 */


@Entity
public class Recipe {

    @Id @GeneratedValue
    private Long recipeId;

    @Column(unique = true)
    String recipeName;

    @ManyToMany
    private Set<Category> categories;

    @ManyToOne
    private User user;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
