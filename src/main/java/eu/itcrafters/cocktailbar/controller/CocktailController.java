package eu.itcrafters.cocktailbar.controller;

import eu.itcrafters.cocktailbar.controller.dto.CocktailRequestDTO;
import eu.itcrafters.cocktailbar.controller.dto.CocktailResponseDTO;
import eu.itcrafters.cocktailbar.service.CocktailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cocktails")
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @PostMapping
    public ResponseEntity<CocktailResponseDTO> createCocktail(@Valid @RequestBody CocktailRequestDTO dto) {
        CocktailResponseDTO created = cocktailService.createCocktail(dto);
        return ResponseEntity.created(URI.create("/api/cocktails/" + created.getId())).body(created);
    }

    @GetMapping
    public List<CocktailResponseDTO> getAllCocktails() {
        return cocktailService.getAllCocktails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CocktailResponseDTO> getCocktailById(@PathVariable Integer id) {
        try {
            CocktailResponseDTO cocktail = cocktailService.getCocktailById(id);
            return ResponseEntity.ok(cocktail);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CocktailResponseDTO> updateCocktail(
            @PathVariable Integer id,
            @Valid @RequestBody CocktailRequestDTO dto) {
        try {
            CocktailResponseDTO updated = cocktailService.updateCocktail(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCocktail(@PathVariable Integer id) {
        try {
            cocktailService.deleteCocktail(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

