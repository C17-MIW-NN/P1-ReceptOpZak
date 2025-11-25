package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.*;

/**
 * @author Pelle Meuzelaar
 * An entity that records the quantity of ingredients per recipe
 */

@Entity
public class RecipeIngredient {

    private static final double DEFAULT_QUANTITY = 100.0;
    @Id @GeneratedValue
    private Long id;

    private Integer quantity;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public Double calculateKcalIngredientPerServing() {
        return (double) quantity * (ingredient.calculateIngredientKcalPerDefaultQuantity() / DEFAULT_QUANTITY);
    }
}
