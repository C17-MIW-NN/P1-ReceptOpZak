package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Sabien Ruijgrok
 * Category entity represents a recipe category, such as "Desserts" or "Snacks".
 * Each category has a name and can be used to group recipes.
 */

@Entity
public class Category {

    @Id @GeneratedValue
    private Long categoryId;

    private String categoryName;

    private boolean favorite;

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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}


