package stan.security.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stan.security.spring_security.models.Category;
import stan.security.spring_security.models.ECategory;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(ECategory name);
}
