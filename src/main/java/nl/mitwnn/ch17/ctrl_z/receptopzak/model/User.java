package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Sybren Bonnema
 * Define characterists for the Author class
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    String userName;

    @OneToMany

}
