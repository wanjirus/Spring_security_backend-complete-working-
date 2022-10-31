package stan.security.spring_security.services.property;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stan.security.spring_security.DTO.PropertyDTO;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.mapper.PropertyMapper;
import stan.security.spring_security.models.*;
import stan.security.spring_security.repository.CategoriesRepository;
import stan.security.spring_security.repository.PropertyRepository;
import stan.security.spring_security.repository.TypeRepository;
import stan.security.spring_security.services.auth.UserService;

import java.util.*;

@Service
@AllArgsConstructor
public class PropertyService implements PropertyServiceInterface {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private  final UserService userService;
    private final CategoriesRepository categoriesRepository;
    private final TypeRepository typeRepository;

    public PropertyDTO findPropertyById(long id) throws ResourceNotFoundException {
     Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No staff record found with Id::" + id));

        return propertyMapper.toPropertyDto(property);
    }


    public PropertyDTO createNewProperty(long userId, PropertyDTO propertyDTO) {
        User user = userService.findUserById(userId);

        Property property = constructStoreObject(propertyDTO);

        Set<Object> strCategory = propertyDTO.getCategories();
        Set<Category> categories = new HashSet<>();

        if(strCategory == null){
            Category constructCategory = categoriesRepository.findByName(ECategory.CONSTRUCT)
                    .orElseThrow(() -> new RuntimeException("Error: No category found with name::" + ECategory.CONSTRUCT));
            categories.add(constructCategory);
        }else {
          strCategory.forEach(category -> {
              if ("farming".equals(category)) {
                  Category farmingCategory = categoriesRepository.findByName(ECategory.FARMING)
                          .orElseThrow(() -> new RuntimeException("Error: No category found with name:"));
                  categories.add(farmingCategory);
              } else if ("estate".equals(category)) {
                  Category estateCategory = categoriesRepository.findByName(ECategory.ESTATE)
                          .orElseThrow(() -> new RuntimeException("Error: No category found "));
                  categories.add(estateCategory);
              } else if ("bill_board".equals(category)) {
                  Category B_boardCategory = categoriesRepository.findByName(ECategory.BILLBOARD)
                          .orElseThrow(() -> new RuntimeException("Error: No category found with name:"));
                  categories.add(B_boardCategory);
              } else if ("commercial".equals(category)) {
                  Category commercial_C = categoriesRepository.findByName(ECategory.COMMERCIAL)
                          .orElseThrow(() -> new RuntimeException("Error: No category found with name:"));
                  categories.add(commercial_C);
              } else {
                  Category constructCategory = categoriesRepository.findByName(ECategory.CONSTRUCT)
                          .orElseThrow(() -> new RuntimeException("Error: No category found with name "));
                  categories.add(constructCategory);
              }
          });
        }
        property.setCategories(categories);
        Set<Object> strType = propertyDTO.getTypes();
        Set<Type> types = new HashSet<>();

        if(strType == null){
            Type typeSale = typeRepository.findByName(EType.SALE)
                    .orElseThrow(() -> new RuntimeException("Error: No category found with name::" + EType.SALE));
            types.add(typeSale);
        }else {
            strType.forEach(type -> {
                if ("sale".equals(type)) {
                    Type typeLease = typeRepository.findByName(EType.LEASE)
                            .orElseThrow(() -> new RuntimeException("Error: No category found with name:"));
                    types.add(typeLease);
                } else if ("rent".equals(type)) {
                    Type typeRent = typeRepository.findByName(EType.RENT)
                            .orElseThrow(() -> new RuntimeException("Error: No category found "));
                    types.add(typeRent);
                } else {
                    Type typeSale = typeRepository.findByName(EType.SALE)
                            .orElseThrow(() -> new RuntimeException("Error: No category found with name "));
                    types.add(typeSale);
                }
            });
        }
        property.setTypes(types);
        property.setUser(user);
        property = propertyRepository.save(property);
        return propertyMapper.toPropertyDto(property);
    }

    public HashMap<String, Boolean> deleteProperty(long id) throws ResourceNotFoundException{
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No staff record found with Id::" + id));
        propertyRepository.delete(property);
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

//    @Override
//    public PropertyDTO updateProperty(long id, PropertyDTO propertyDTO){
//
//        Optional<Property> property = propertyRepository.findById(id);
//        propertyDTO.setName(propertyDTO.getName());
////
////        property.setName(propertyDTO.getName());
////        property.setLocation(propertyDTO.getLocation());
////        property.setPhoneNumber(propertyDTO.getPhoneNumber());
////        property.setPrice(propertyDTO.getPrice());
////        property.setContactInfo(propertyDTO.getContactInfo());
////        property.setDescription(propertyDTO.getDescription());
//        Property updateProperty = propertyRepository.save(property)
//        return propertyMapper.toPropertyDto(updateProperty);
//
//    }
//    public ResponseEntity<HashMap<String, Boolean>> deleteProperty (long id){
//        Optional<Property> property = propertyRepository.findById(id);
//        propertyRepository.delete(property);
//        HashMap<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }



    public List<PropertyDTO> findAll(){
        List<Property> allProperties = propertyRepository.findAll();
        return propertyMapper.toPropertyDtoList(allProperties);
    }

    @Override
    public List<PropertyDTO> findPropertyByUserId(long userId) {
        List<Property> propertyList = propertyRepository.findByUserId(userId);
        return propertyMapper.toPropertyDtoList(propertyList);
    }

//    @Override
//    public List<PropertyDTO> findPropertyByType(Long typeId) {
//        Optional<Type> type = typeRepository.findByName(EType.SALE);
////        List<Property> propertyList = propertyRepository.findByTypeSale(type);
////        return propertyMapper.toPropertyDto(propertyList);
////    }
//
//
//    @Override
//    public List<PropertyDTO> findPropertyByType(long typeId) {
//        List<Property> propertyList = propertyRepository.findByUserId(userId);
//        return null;
//    }
//




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
                .status(propertyDTO.getStatus())
                .dimensions(propertyDTO.getDimensions())
//                .phoneNumber(propertyDTO.getPhoneNumber())
                .contactInfo(propertyDTO.getContactInfo())

                .description(propertyDTO.getDescription())
                .build();
    }

}
