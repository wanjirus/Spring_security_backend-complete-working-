package stan.inc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stan.inc.models.property.PropertyImages;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImages, Long> {
    List<PropertyImages> findAllByPropertyId(Long propertyId);
}
