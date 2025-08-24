package eu.itcrafters.cocktailbar.controller;

import eu.itcrafters.cocktailbar.persistence.Cocktail;
import eu.itcrafters.cocktailbar.persistence.CocktailRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cocktails")
public class CocktailController {

    private CocktailRepository cocktailRepository;

    @PostMapping
    public ResponseEntity<Object> createCocktail(@Valid @RequestBody Cocktail cocktail) {
        if (cocktailRepository.existsByName(cocktail.getName())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Cocktail with name '" + cocktail.getName() + "' already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        Cocktail savedCocktail = cocktailRepository.save(cocktail);
        URI location = URI.create("/cocktails/" + savedCocktail.getId());

        return ResponseEntity.created(location).body(savedCocktail);
    }

    @GetMapping
    public List<Cocktail> getAllCocktails() {
        return cocktailRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cocktail> getCocktailById(@PathVariable Integer id) {
        return cocktailRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cocktail> updateCocktail(
            @PathVariable Integer id,
            @Valid @RequestBody Cocktail updatedCocktail) {

        return cocktailRepository.findById(id).map(cocktail -> {
            cocktail.setName(updatedCocktail.getName());
            cocktail.setDescription(updatedCocktail.getDescription());
            return ResponseEntity.ok(cocktailRepository.save(cocktail));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCocktail(@PathVariable Integer id) {
        if (!cocktailRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cocktailRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

