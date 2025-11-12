package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Pelle Meuzelaar
 * Defines characteristics for Ingredient class
 */

@Entity
public class Ingredient {

    @Id @GeneratedValue
    private Long ingredientId;

    private String ingredientName;

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
