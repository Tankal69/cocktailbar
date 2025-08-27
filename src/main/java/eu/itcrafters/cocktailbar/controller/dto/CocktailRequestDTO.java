package eu.itcrafters.cocktailbar.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CocktailRequestDTO {
    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 20)
    private String description;

    @NotNull
    private List<IngredientDTO> ingredients;
}
