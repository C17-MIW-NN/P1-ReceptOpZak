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

    private static final int KCAL_PER_GRAM_CARB = 4;
    @Id @GeneratedValue
    private Long ingredientId;

    private String ingredientName;
    private Integer ingredientCarb;
    private Integer ingredientFat;
    private Integer ingredientProtein;
    private Integer ingredientKcal;

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

    public Integer getIngredientCarb() {
        return ingredientCarb;
    }

    public void setIngredientCarb(Integer ingredientCarb) {
        this.ingredientCarb = ingredientCarb;
    }

    public Integer getIngredientFat() {
        return ingredientFat;
    }

    public void setIngredientFat(Integer ingredientFat) {
        this.ingredientFat = ingredientFat;
    }

    public Integer getIngredientProtein() {
        return ingredientProtein;
    }

    public void setIngredientProtein(Integer ingredientProtein) {
        this.ingredientProtein = ingredientProtein;
    }

    public Integer getIngredientKcal() {
        return ingredientKcal;
    }

    public void setIngredientKcal() {
        int carb = ingredientCarb != null ? ingredientCarb : 0;
        int fat = ingredientFat != null ? ingredientFat : 0;
        int protein = ingredientProtein != null ? ingredientProtein : 0;
        this.ingredientKcal = KCAL_PER_GRAM_CARB * carb + 9 * fat + 4 * protein;
    }
}
