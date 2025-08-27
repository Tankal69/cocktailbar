package eu.itcrafters.cocktailbar.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    boolean existsByName(String name);

    Optional<Ingredient> findByName(String name);
}
