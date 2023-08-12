package stan.inc.service.property;//package stan.security.spring_security.services.property;


import stan.inc.DTO.PropertyDTO;
import stan.inc.exceptions.ResourceNotFoundException;
import stan.inc.models.property.Property;

import java.util.HashMap;
import java.util.List;

/**
 * Author::Stanley
 * Since
 * Version 1.0.0
 */
public interface PropertyServiceInterface {
    PropertyDTO createNewProperty(long userId, PropertyDTO propertyDTO);

    HashMap<String, Boolean> deleteProperty(long id);

    List<PropertyDTO> findPropertyByUserId(long userId);

    List<PropertyDTO> findAll();

    abstract Property findPropertyById(long id) throws ResourceNotFoundException;

    Boolean existByUserId(Long UserId);

}
