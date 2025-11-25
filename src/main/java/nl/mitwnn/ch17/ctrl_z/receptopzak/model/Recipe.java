package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sabien Ruijgrok
 * Represents a recipe with its name, description, image, creator, categories, ingredients, and instructions.
 */

@Entity
public class Recipe {

    @Id @GeneratedValue
    private Long recipeId;

    @Column(unique = true)
    String recipeName;

    private String description;

    private String imageURL;

    @ManyToOne
    private RecipeUser recipeUser;

    @ManyToMany
    private Set<Category> categories;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instruction> instructions = new ArrayList<>();

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", name='" + recipeName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    public int countFavoriteCategories() {
        if (categories == null) return 0;

        int count = 0;
        for (Category category : categories) {
            if (category.isFavorite()) {
                count++;
            }
        }
        return count;
    }

    @Transient public double getTotalKcal() {
        double total = 0.0;

        for (RecipeIngredient recipeIngredient : recipeIngredients) {
            if (recipeIngredient != null) {
                total += recipeIngredient.calculateKcalIngredientPerServing();
            }
        }
        return total;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public RecipeUser getRecipeUser() {
        return recipeUser;
    }

    public void setRecipeUser(RecipeUser recipeUser) {
        this.recipeUser = recipeUser;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

}
