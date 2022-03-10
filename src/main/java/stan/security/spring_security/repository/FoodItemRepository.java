package stan.security.spring_security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stan.security.spring_security.models.ERole;
import stan.security.spring_security.models.FoodItem;
import stan.security.spring_security.models.Role;

import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

}