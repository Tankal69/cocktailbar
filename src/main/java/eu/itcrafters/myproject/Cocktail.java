package eu.itcrafters.myproject;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "COCKTAIL")
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @Size(max = 20)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 20)
    private String description;

}