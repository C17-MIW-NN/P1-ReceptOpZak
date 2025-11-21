package nl.mitwnn.ch17.ctrl_z.receptopzak.repositories;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sybren Bonnema
 */

public interface InstructionRepository extends JpaRepository<Instruction, Long> {
}