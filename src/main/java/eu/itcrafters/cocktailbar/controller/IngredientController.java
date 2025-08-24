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

    private IngredientRepository ingredientRepository;

    @PostMapping
    public ResponseEntity<Object> createIngredient(@Valid @RequestBody Ingredient ingredient) {
        if (ingredientRepository.existsByName(ingredient.getName())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Ingredient '" + ingredient.getName() + "' already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        URI location = URI.create("/ingredients/" + savedIngredient.getId());

        return ResponseEntity.created(location).body(savedIngredient);
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Integer id) {
        return ingredientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(
            @PathVariable Integer id,
            @Valid @RequestBody Ingredient updatedIngredient) {

        return ingredientRepository.findById(id).map(ingredient -> {
            ingredient.setName(updatedIngredient.getName());
            return ResponseEntity.ok(ingredientRepository.save(ingredient));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) {
        if (!ingredientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ingredientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
