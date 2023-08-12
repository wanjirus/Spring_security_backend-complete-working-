package stan.inc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stan.inc.models.property.Property;
import stan.inc.models.user.User;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
//    List <Property> findAllByByUser(User user);
    List<Property> findByUserId(long id);

    List<Property>findByTypesId(Integer typesId);

    List<Property>findByTypesIdAndCategoriesId(Integer typesId, Integer categoriesId);

    void deleteByTypesId(Integer typesId);
    void deleteByCategoriesId(Integer categoriesId);


    Boolean existsPropertiesByUser(User id);



//    List<Property> findByTypeSale(Optional<Type> type);
//    void delete(Optional<Property> property);
         }