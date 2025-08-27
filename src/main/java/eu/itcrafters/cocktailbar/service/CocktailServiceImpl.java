package eu.itcrafters.cocktailbar.service;

import eu.itcrafters.cocktailbar.controller.dto.CocktailRequestDTO;
import eu.itcrafters.cocktailbar.controller.dto.CocktailResponseDTO;
import eu.itcrafters.cocktailbar.controller.dto.IngredientDTO;
import eu.itcrafters.cocktailbar.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import eu.itcrafters.cocktailbar.infrastructure.rest.exception.DataNotFoundException;


import java.util.*;

@Service
public class CocktailServiceImpl implements CocktailService {

    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;
    private final CocktailIngredientRepository cocktailIngredientRepository;

    public CocktailServiceImpl(CocktailRepository cocktailRepository,
                               IngredientRepository ingredientRepository,
                               CocktailIngredientRepository cocktailIngredientRepository) {
        this.cocktailRepository = cocktailRepository;
        this.ingredientRepository = ingredientRepository;
        this.cocktailIngredientRepository = cocktailIngredientRepository;
    }

    @Override
    @Transactional
    public CocktailResponseDTO createCocktail(CocktailRequestDTO dto) {
        if (cocktailRepository.existsByName(dto.getName())) {
            throw new DataNotFoundException("Cocktail with name '" + dto.getName() + "' already exists.");
        }


        Cocktail cocktail = new Cocktail();
        cocktail.setName(dto.getName());
        cocktail.setDescription(dto.getDescription());
        Cocktail savedCocktail = cocktailRepository.save(cocktail);

        List<CocktailIngredient> cocktailIngredients = new ArrayList<>();

        for (IngredientDTO ingredientDTO : dto.getIngredients()) {
            Ingredient ingredient = ingredientRepository
                    .findByName(ingredientDTO.getName())
                    .orElseGet(() -> {
                        Ingredient newIngredient = new Ingredient();
                        newIngredient.setName(ingredientDTO.getName());
                        return ingredientRepository.save(newIngredient);
                    });

            CocktailIngredient ci = new CocktailIngredient();
            ci.setCocktail(savedCocktail);
            ci.setIngredient(ingredient);
            ci.setQuantity(ingredientDTO.getQuantity());
            cocktailIngredients.add(ci);
        }

        cocktailIngredientRepository.saveAll(cocktailIngredients);

        return mapToResponseDTO(savedCocktail, cocktailIngredients);
    }

    @Override
    public List<CocktailResponseDTO> getAllCocktails() {
        List<Cocktail> cocktails = cocktailRepository.findAll();

        return cocktails.stream()
                .map(cocktail -> {
                    List<CocktailIngredient> ingredients = cocktailIngredientRepository
                            .findAll()
                            .stream()
                            .filter(ci -> ci.getCocktail().getId().equals(cocktail.getId()))
                            .toList();
                    return mapToResponseDTO(cocktail, ingredients);
                })
                .toList();
    }

    @Override
    public CocktailResponseDTO getCocktailById(Integer id) {
        Cocktail cocktail = cocktailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cocktail not found"));

        List<CocktailIngredient> ingredients = cocktailIngredientRepository
                .findAll()
                .stream()
                .filter(ci -> ci.getCocktail().getId().equals(cocktail.getId()))
                .toList();

        return mapToResponseDTO(cocktail, ingredients);
    }

    @Override
    @Transactional
    public CocktailResponseDTO updateCocktail(Integer id, CocktailRequestDTO dto) {
        Cocktail cocktail = cocktailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cocktail not found"));

        cocktail.setName(dto.getName());
        cocktail.setDescription(dto.getDescription());
        cocktailRepository.save(cocktail);

        cocktailIngredientRepository
                .findAll()
                .stream()
                .filter(ci -> ci.getCocktail().getId().equals(cocktail.getId()))
                .forEach(ci -> cocktailIngredientRepository.deleteById(ci.getId()));

        List<CocktailIngredient> newIngredients = new ArrayList<>();
        for (IngredientDTO ingredientDTO : dto.getIngredients()) {
            Ingredient ingredient = ingredientRepository
                    .findByName(ingredientDTO.getName())
                    .orElseGet(() -> {
                        Ingredient newIngredient = new Ingredient();
                        newIngredient.setName(ingredientDTO.getName());
                        return ingredientRepository.save(newIngredient);
                    });

            CocktailIngredient ci = new CocktailIngredient();
            ci.setCocktail(cocktail);
            ci.setIngredient(ingredient);
            ci.setQuantity(ingredientDTO.getQuantity());
            newIngredients.add(ci);
        }

        cocktailIngredientRepository.saveAll(newIngredients);

        return mapToResponseDTO(cocktail, newIngredients);
    }

    @Override
    public void deleteCocktail(Integer id) {
        if (!cocktailRepository.existsById(id)) {
            throw new DataNotFoundException("Cocktail with ID '" + id + "' not found.");
        }
        
        cocktailIngredientRepository
                .findAll()
                .stream()
                .filter(ci -> ci.getCocktail().getId().equals(id))
                .forEach(ci -> cocktailIngredientRepository.deleteById(ci.getId()));

        cocktailRepository.deleteById(id);
    }

    private CocktailResponseDTO mapToResponseDTO(Cocktail cocktail, List<CocktailIngredient> ingredients) {
        CocktailResponseDTO response = new CocktailResponseDTO();
        response.setId(cocktail.getId());
        response.setName(cocktail.getName());
        response.setDescription(cocktail.getDescription());

        List<IngredientDTO> ingredientDTOs = ingredients.stream().map(ci -> {
            IngredientDTO dto = new IngredientDTO();
            dto.setName(ci.getIngredient().getName());
            dto.setQuantity(ci.getQuantity());
            return dto;
        }).toList();

        response.setIngredients(ingredientDTOs);
        return response;
    }
}
