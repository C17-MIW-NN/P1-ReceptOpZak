package nl.mitwnn.ch17.ctrl_z.receptopzak.repositories;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sybren Bonnema
 * Allows for the saving editing and deleting of recipe instructions in the SQL database.
 */

public interface InstructionRepository extends JpaRepository<Instruction, Long> {
}