package stan.security.spring_security.services.property;
/**
 * Author::Stanley
 * Since
 * Version 1.0.0
 */

import lombok.AllArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import stan.security.spring_security.DTO.PropertyDTO;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.mapper.PropertyMapper;
import stan.security.spring_security.models.Property;
import stan.security.spring_security.models.User;
import stan.security.spring_security.repository.PropertyRepository;
import stan.security.spring_security.services.auth.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class PropertyService implements PropertyServiceInterface {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private  final UserService userService;



    public PropertyDTO findPropertyById(long id) throws ResourceNotFoundException {
     Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No staff record found with Id::" + id));

        return propertyMapper.toPropertyDto(property);
    }

    public PropertyDTO createNewProperty(long userId, PropertyDTO propertyDTO) {
        User user = userService.findUserById(userId);
        Property property = constructStoreObject(propertyDTO);
        property.setUser(user);
        property = propertyRepository.save(property);
        return propertyMapper.toPropertyDto(property);
    }

    public List<PropertyDTO> findAll(){
        List<Property> allProperties = propertyRepository.findAll();
        return propertyMapper.toPropertyDtoList(allProperties);
    }

    @Override
    public List<PropertyDTO> findPropertyByUserId(long userId) {
        List<Property> propertyList = propertyRepository.findByUserId(userId);
        return propertyMapper.toPropertyDtoList(propertyList);
    }


    @Override
    public Boolean existByUserId(Long UserId) {
        User user = userService.findUserById(UserId);
        boolean property = propertyRepository.existsPropertiesByUser(user);
        return property;
//        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }


    //
    private Property constructStoreObject(PropertyDTO propertyDTO) {
        return Property.builder().name(propertyDTO.getName())
                .location(propertyDTO.getLocation())
                .price(propertyDTO.getPrice())
                .phoneNumber(propertyDTO.getPhoneNumber())
                .contactInfo(propertyDTO.getContactInfo())
                .description(propertyDTO.getDescription())
                .build();
    }

}
