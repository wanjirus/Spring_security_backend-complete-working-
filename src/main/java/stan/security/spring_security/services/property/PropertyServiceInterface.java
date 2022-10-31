package stan.security.spring_security.services.property;


import stan.security.spring_security.DTO.PropertyDTO;

import java.util.HashMap;
import java.util.List;

/**
 * Author::Stanley
 * Since
 * Version 1.0.0
 */
public interface PropertyServiceInterface {
    PropertyDTO createNewProperty(long userId, PropertyDTO propertyDTO);
//    public PropertyDTO updateProperty(long id, PropertyDTO propertyDTO);
    HashMap<String, Boolean> deleteProperty(long id);

    List<PropertyDTO> findPropertyByUserId(long userId);

//    List<PropertyDTO>findPropertyByCategorySale();
    List<PropertyDTO>findAll();
    PropertyDTO findPropertyById(long UserId);

//    List<PropertyDTO> findPropertyByTypeSale();

    Boolean existByUserId(Long UserId);


//    PropertyDTO findById(long id);
}
