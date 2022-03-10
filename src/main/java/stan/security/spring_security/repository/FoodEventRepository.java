package stan.security.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stan.security.spring_security.models.FoodEvent;
import stan.security.spring_security.models.Role;

@Repository

public interface FoodEventRepository extends JpaRepository<FoodEvent, Long> {
}