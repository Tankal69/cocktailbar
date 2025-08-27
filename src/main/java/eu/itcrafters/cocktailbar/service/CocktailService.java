package eu.itcrafters.cocktailbar.service;

import eu.itcrafters.cocktailbar.controller.dto.CocktailRequestDTO;
import eu.itcrafters.cocktailbar.controller.dto.CocktailResponseDTO;

import java.util.List;

public interface CocktailService {
    CocktailResponseDTO createCocktail(CocktailRequestDTO dto);
    List<CocktailResponseDTO> getAllCocktails();
    CocktailResponseDTO getCocktailById(Integer id);
    CocktailResponseDTO updateCocktail(Integer id, CocktailRequestDTO dto);
    void deleteCocktail(Integer id);
}
