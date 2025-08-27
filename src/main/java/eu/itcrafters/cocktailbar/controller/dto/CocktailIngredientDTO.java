package eu.itcrafters.cocktailbar.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CocktailIngredientDTO {
    private Integer id;
    private Integer cocktailId;
    private String cocktailName;
    private Integer ingredientId;
    private String ingredientName;
    private String quantity;
}

