package eu.itcrafters.cocktailbar.controller;

import eu.itcrafters.cocktailbar.persistence.Cocktail;
import eu.itcrafters.cocktailbar.persistence.CocktailRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cocktails")
public class CocktailController {

    @Autowired
    private CocktailRepository cocktailRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<Cocktail> createCocktail(@Valid @RequestBody Cocktail cocktail) {
        return ResponseEntity.ok(cocktailRepository.save(cocktail));
    }

    // READ ALL
    @GetMapping
    public List<Cocktail> getAllCocktails() {
        return cocktailRepository.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Cocktail> getCocktailById(@PathVariable Integer id) {
        return cocktailRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
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

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCocktail(@PathVariable Integer id) {
        if (!cocktailRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cocktailRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

