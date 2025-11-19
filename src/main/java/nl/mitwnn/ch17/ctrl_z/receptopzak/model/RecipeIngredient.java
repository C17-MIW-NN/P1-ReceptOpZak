package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.*;

/**
 * @author Pelle Meuzelaar
 * An entity that records the quantity of ingredients per recipe
 */

@Entity
public class RecipeIngredient {

    @Id @GeneratedValue
    private Long id;

    private int quantity;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Ingredient ingredient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
