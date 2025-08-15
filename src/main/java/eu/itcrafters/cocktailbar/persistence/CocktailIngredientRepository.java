package eu.itcrafters.cocktailbar.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, Integer> {
    // You can add custom query methods here if needed later
}
