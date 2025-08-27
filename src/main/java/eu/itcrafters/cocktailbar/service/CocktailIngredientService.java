package eu.itcrafters.cocktailbar.service;

import eu.itcrafters.cocktailbar.controller.dto.CocktailIngredientDTO;

import java.util.List;

public interface CocktailIngredientService {
    List<CocktailIngredientDTO> getAllCocktailIngredients();
    List<CocktailIngredientDTO> getIngredientsByCocktailId(Integer cocktailId);
}

