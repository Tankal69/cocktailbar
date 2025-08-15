package eu.itcrafters.cocktailbar.controller;

import eu.itcrafters.cocktailbar.persistence.Ingredient;
import eu.itcrafters.cocktailbar.persistence.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createIngredient(@Valid @RequestBody Ingredient ingredient) {
        if (ingredientRepository.existsByName(ingredient.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ingredient '" + ingredient.getName() + "' already exists.");
        }
        return ResponseEntity.ok(ingredientRepository.save(ingredient));
    }

    // READ ALL
    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Integer id) {
        return ingredientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(
            @PathVariable Integer id,
            @Valid @RequestBody Ingredient updatedIngredient) {

        return ingredientRepository.findById(id).map(ingredient -> {
            ingredient.setName(updatedIngredient.getName());
            return ResponseEntity.ok(ingredientRepository.save(ingredient));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) {
        if (!ingredientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ingredientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
