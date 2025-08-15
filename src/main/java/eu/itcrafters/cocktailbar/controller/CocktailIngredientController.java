package eu.itcrafters.cocktailbar.controller;

import eu.itcrafters.cocktailbar.persistence.CocktailIngredient;
import eu.itcrafters.cocktailbar.persistence.CocktailIngredientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cocktail-ingredients")
public class CocktailIngredientController {

    @Autowired
    private CocktailIngredientRepository cocktailIngredientRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<CocktailIngredient> createCocktailIngredient(@Valid @RequestBody CocktailIngredient ci) {
        return ResponseEntity.ok(cocktailIngredientRepository.save(ci));
    }

    // READ ALL
    @GetMapping
    public List<CocktailIngredient> getAll() {
        return cocktailIngredientRepository.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<CocktailIngredient> getById(@PathVariable Integer id) {
        return cocktailIngredientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CocktailIngredient> update(@PathVariable Integer id,
                                                     @Valid @RequestBody CocktailIngredient updatedCI) {
        return cocktailIngredientRepository.findById(id).map(existing -> {
            existing.setCocktail(updatedCI.getCocktail());
            existing.setIngredient(updatedCI.getIngredient());
            existing.setQuantity(updatedCI.getQuantity());
            return ResponseEntity.ok(cocktailIngredientRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!cocktailIngredientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cocktailIngredientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

