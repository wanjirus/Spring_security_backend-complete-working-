package stan.security.spring_security.services.property;

import stan.security.spring_security.DTO.PropertyDTO;

import javax.validation.Valid;
import java.util.List;

/**
 * Author::Stanley
 * Since
 * Version 1.0.0
 */
public interface PropertyServiceInterface {
    public PropertyDTO createNewProperty(long userId, PropertyDTO propertyDTO);
    public List<PropertyDTO> findPropertyByUserId(long userId);
    public PropertyDTO findPropertyById(long storeId);
    public Boolean existByUserId(Long UserId);

}
