package eu.itcrafters.cocktailbar.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "COCKTAIL_INGREDIENT")
public class CocktailIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "COCKTAIL_ID", nullable = false)
    private Cocktail cocktail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "INGREDIENT_ID", nullable = false)
    private Ingredient ingredient;

    @Size(max = 20)
    @NotNull
    @Column(name = "QUANTITY", nullable = false, length = 20)
    private String quantity;
}
