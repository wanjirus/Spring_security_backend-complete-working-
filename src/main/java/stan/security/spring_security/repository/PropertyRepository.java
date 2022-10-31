package stan.security.spring_security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stan.security.spring_security.models.Property;
import stan.security.spring_security.models.Type;
import stan.security.spring_security.models.User;

import java.util.List;
import java.util.Optional;

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