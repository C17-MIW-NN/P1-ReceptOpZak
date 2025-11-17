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
    private static final int KCAL_PER_GRAM_FAT = 9;
    private static final int KCAL_PER_GRAM_PROTEIN = 4;
    private static final int DEFAULT_CARB = 0;
    private static final int DEFAULT_FAT = 0;
    private static final int DEFAULT_PROTEIN = 0;

    @Id @GeneratedValue
    private Long ingredientId;

    private String ingredientName;
    private Integer ingredientCarb;
    private Integer ingredientFat;
    private Integer ingredientProtein;
    private Integer ingredientKcal;


    public Ingredient(String name, int carb, int fat, int protein) {
        this.ingredientName = name;
        this.ingredientCarb = carb;
        this.ingredientFat = fat;
        this.ingredientProtein = protein;
        setIngredientKcal();
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
        this.ingredientCarb = DEFAULT_CARB;
        this.ingredientFat = DEFAULT_FAT;
        this.ingredientProtein = DEFAULT_PROTEIN;
        setIngredientKcal();
    }

    public Ingredient() {
    }

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
        this.ingredientKcal = KCAL_PER_GRAM_CARB * ingredientCarb + KCAL_PER_GRAM_FAT * ingredientFat +
                KCAL_PER_GRAM_PROTEIN * ingredientProtein;
    }
}
