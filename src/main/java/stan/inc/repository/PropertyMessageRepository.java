package stan.inc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stan.inc.models.property.PropertyMessage;

import java.util.List;

@Repository
public interface PropertyMessageRepository extends JpaRepository<PropertyMessage, Long> {

    List<PropertyMessage> findByPropertyId(long id);
//    Boolean existsPropertiesByUser(User id);


}
