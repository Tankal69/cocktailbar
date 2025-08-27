package eu.itcrafters.cocktailbar.service;

import eu.itcrafters.cocktailbar.controller.dto.CocktailIngredientDTO;
import eu.itcrafters.cocktailbar.persistence.CocktailIngredient;
import eu.itcrafters.cocktailbar.persistence.CocktailIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocktailIngredientServiceImpl implements CocktailIngredientService {

    private final CocktailIngredientRepository cocktailIngredientRepository;

    public CocktailIngredientServiceImpl(CocktailIngredientRepository cocktailIngredientRepository) {
        this.cocktailIngredientRepository = cocktailIngredientRepository;
    }

    @Override
    public List<CocktailIngredientDTO> getAllCocktailIngredients() {
        return cocktailIngredientRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<CocktailIngredientDTO> getIngredientsByCocktailId(Integer cocktailId) {
        return cocktailIngredientRepository.findByCocktailId(cocktailId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private CocktailIngredientDTO mapToDTO(CocktailIngredient ci) {
        CocktailIngredientDTO dto = new CocktailIngredientDTO();
        dto.setId(ci.getId());
        dto.setCocktailId(ci.getCocktail().getId());
        dto.setCocktailName(ci.getCocktail().getName());
        dto.setIngredientId(ci.getIngredient().getId());
        dto.setIngredientName(ci.getIngredient().getName());
        dto.setQuantity(ci.getQuantity());
        return dto;
    }
}

