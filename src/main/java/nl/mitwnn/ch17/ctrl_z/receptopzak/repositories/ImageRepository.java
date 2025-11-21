package nl.mitwnn.ch17.ctrl_z.receptopzak.repositories;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * @author Sabien Ruijgrok
 */

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByFileName(String fileName);
}
