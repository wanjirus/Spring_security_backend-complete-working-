package stan.inc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stan.inc.models.property.EType;
import stan.inc.models.property.Type;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(EType name);
}
