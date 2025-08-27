package eu.itcrafters.cocktailbar.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, Integer> {

    List<CocktailIngredient> findByCocktailId(Integer cocktailId);
}

