package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Sabien Ruijgrok
 * Category entity represents a recipe category
 * Each category has a name and can be used to group recipes
 */

@Entity
public class Category {

    @Id @GeneratedValue
    private Long categoryId;

    private String categoryName;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}


