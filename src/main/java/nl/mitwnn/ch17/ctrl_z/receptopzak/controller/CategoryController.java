package nl.mitwnn.ch17.ctrl_z.receptopzak.controller;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Category;
import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Recipe;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Sabien Ruijgrok
 * Category can be added and changed
 */

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Show categories
    @GetMapping("/all")
    public String showCategoryOverview(Model datamodel) {
        datamodel.addAttribute("allCategories", categoryRepository.findAll());
        datamodel.addAttribute("formCategories", new Category());

        return "categoryOverview";
    }

    // Save categories
    @PostMapping("/save")
    public String saveOrUpdateCategory(@ModelAttribute("formCategories") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/category/all";
        }

        categoryRepository.save(category);
        return "redirect:/category/all";
    }

    // Delete categories
    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return "redirect:/category/all";
    }
}
