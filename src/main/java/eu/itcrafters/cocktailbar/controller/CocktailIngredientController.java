package eu.itcrafters.cocktailbar.controller;

import eu.itcrafters.cocktailbar.controller.dto.CocktailIngredientDTO;
import eu.itcrafters.cocktailbar.service.CocktailIngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cocktail-ingredients")
public class CocktailIngredientController {

    private final CocktailIngredientService cocktailIngredientService;

    public CocktailIngredientController(CocktailIngredientService cocktailIngredientService) {
        this.cocktailIngredientService = cocktailIngredientService;
    }

    @GetMapping
    public ResponseEntity<List<CocktailIngredientDTO>> getAll() {
        return ResponseEntity.ok(cocktailIngredientService.getAllCocktailIngredients());
    }

    @GetMapping("/cocktail/{cocktailId}")
    public ResponseEntity<List<CocktailIngredientDTO>> getByCocktailId(@PathVariable Integer cocktailId) {
        return ResponseEntity.ok(cocktailIngredientService.getIngredientsByCocktailId(cocktailId));
    }
}
