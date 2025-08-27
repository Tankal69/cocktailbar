package eu.itcrafters.cocktailbar.controller;

import eu.itcrafters.cocktailbar.persistence.Ingredient;
import eu.itcrafters.cocktailbar.persistence.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private static final String ERROR_KEY = "error";
    private static final String INGREDIENT_NOT_FOUND = "Ingredient not found";

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @PostMapping
    public ResponseEntity<Object> createIngredient(@Valid @RequestBody Ingredient ingredient) {
        if (ingredientRepository.existsByName(ingredient.getName())) {
            Map<String, String> error = new HashMap<>();
            error.put(ERROR_KEY, "Ingredient '" + ingredient.getName() + "' already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        URI location = URI.create("/api/ingredients/" + savedIngredient.getId());
        return ResponseEntity.created(location).body(savedIngredient);
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ResponseEntity.ok(ingredients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIngredientById(@PathVariable Integer id) {
        return ingredientRepository.findById(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(ERROR_KEY, INGREDIENT_NOT_FOUND)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateIngredient(
            @PathVariable Integer id,
            @Valid @RequestBody Ingredient updatedIngredient) {

        return ingredientRepository.findById(id)
                .<ResponseEntity<Object>>map(ingredient -> {
                    ingredient.setName(updatedIngredient.getName());
                    Ingredient saved = ingredientRepository.save(ingredient);
                    return ResponseEntity.ok(saved);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(ERROR_KEY, INGREDIENT_NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIngredient(@PathVariable Integer id) {
        if (!ingredientRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(ERROR_KEY, INGREDIENT_NOT_FOUND));
        }
        ingredientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
