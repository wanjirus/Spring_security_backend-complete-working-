package stan.security.spring_security.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stan.security.spring_security.models.Store;
import stan.security.spring_security.models.User;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List <Store> findByUser(User user);

}