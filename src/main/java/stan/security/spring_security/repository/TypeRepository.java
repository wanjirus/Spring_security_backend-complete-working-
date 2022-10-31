package stan.security.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stan.security.spring_security.models.ERole;
import stan.security.spring_security.models.EType;
import stan.security.spring_security.models.Role;
import stan.security.spring_security.models.Type;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(EType name);
}
