package eu.itcrafters.cocktailbar.controller.dto;

import lombok.Data;
import java.util.List;

@Data
public class CocktailResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private List<IngredientDTO> ingredients;
}