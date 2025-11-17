package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.*;

/**
 * @author Sybren Bonnema
 * Class for defining and implementing recipe instructions functionality.
 */

@Entity
public class Instruction {

    @Id
    @GeneratedValue
    private Long id;

    private int stepNumber;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    private Recipe recipe;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
