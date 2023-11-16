package hu.pcvilag.app.models;

import javax.persistence.*;

@Entity
@Table(name = "secu_roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public String getName() {
        return this.name;
    }

}