package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.*;

import java.util.List;
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

    private String description;

    private String instructions;

    private String imageURL;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<Category> categories;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients;

//    @Transient
//    private Ingredient newIngredient;

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", name='" + recipeName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

//    public Ingredient getNewIngredient() { return newIngredient; }
//
//    public void setNewIngredient(Ingredient newIngredient) { this.newIngredient = newIngredient; }

}
