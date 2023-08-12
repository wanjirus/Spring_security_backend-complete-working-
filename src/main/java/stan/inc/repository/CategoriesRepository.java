package stan.inc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stan.inc.models.property.Category;
import stan.inc.models.property.ECategory;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(ECategory name);
}
