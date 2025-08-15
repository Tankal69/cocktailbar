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

    @NotNull
    @Column(name = "COCKTAIL_ID", nullable = false)
    private Integer cocktailId;

    @NotNull
    @Column(name = "INGREDIENT_ID", nullable = false)
    private Integer ingredientId;

    @Size(max = 20)
    @NotNull
    @Column(name = "QUANTITY", nullable = false, length = 20)
    private String quantity;

}