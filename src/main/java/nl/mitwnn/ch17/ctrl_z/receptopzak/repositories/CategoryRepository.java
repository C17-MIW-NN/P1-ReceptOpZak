package nl.mitwnn.ch17.ctrl_z.receptopzak.repositories;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sabien Ruijgrok
 */

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
