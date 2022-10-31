package stan.security.spring_security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stan.security.spring_security.DTO.PropertyDTO;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.models.Property;
import stan.security.spring_security.models.Type;
import stan.security.spring_security.models.User;
import stan.security.spring_security.repository.PropertyRepository;
import stan.security.spring_security.repository.TypeRepository;
import stan.security.spring_security.services.TypeService;
import stan.security.spring_security.services.property.PropertyServiceInterface;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property")
public class PropertyController {
//TODO define services for the methods

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private TypeRepository typeRepository;


    private TypeService typeService;
    private final PropertyServiceInterface propertyServiceInterface;


    @PostMapping()
    public ResponseEntity<Object> createProperty(@RequestParam Long userId, @Valid @RequestBody PropertyDTO propertyDTO)
            throws ResourceNotFoundException {
        try {
            PropertyDTO returnedPropertyDTO = propertyServiceInterface.createNewProperty(userId,propertyDTO);
            return new ResponseEntity<>(returnedPropertyDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) throws ResourceNotFoundException{
        try {
         Property property = propertyRepository.getById(id);
            property.setName(propertyDTO.getName());
            property.setContactInfo(propertyDTO.getContactInfo());
            property.setDescription(propertyDTO.getDescription());
            property.setPrice(propertyDTO.getPrice());
            property.setStatus(propertyDTO.getStatus());
            property.setDimensions(propertyDTO.getDimensions());
//            property.setOwner(property.getOwner());
//            property.setPhoneNumber(propertyDTO.getPhoneNumber());
            property.setLocation(propertyDTO.getLocation());
            Property property1 = propertyRepository.save(property);
            return new ResponseEntity<>(property1, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/properties")
    public ResponseEntity <Object> getAllProperties() {
        try {
            List<PropertyDTO> propertyDTO = propertyServiceInterface.findAll();
            return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getPropertyByUserId(@RequestParam Long userId) throws ResourceNotFoundException{
        try {
            List<PropertyDTO> propertyDTOS = propertyServiceInterface.findPropertyByUserId(userId);
            return new ResponseEntity<>(propertyDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/properties/{typesId}/type")
    public ResponseEntity<List<Property>> getAllPropertiesByTypeId(@PathVariable("typesId") Long typesId) {
        if (!propertyRepository.existsById(typesId)) {
            throw new ResourceNotFoundException("Not found properties ");
        }
        List<Property> properties = propertyRepository.findByTypesId(Math.toIntExact(typesId));
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/properties/{typesId}/{categoryId}/type")
    public ResponseEntity<List<Property>> getAllPropertiesByTypeIdAndCategoryId(@PathVariable("typesId") Long typesId, @PathVariable("categoryId") Long categoriesId){
        if (!propertyRepository.existsById(typesId) && !propertyRepository.existsById(categoriesId)) {
            throw new ResourceNotFoundException("Not found properties ");
        }
        List<Property> properties = propertyRepository.findByTypesIdAndCategoriesId(Math.toIntExact(typesId), Math.toIntExact(categoriesId));
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }


//    @GetMapping("/sale/{typeId}")
//    public ResponseEntity<Object> getSaleProperties(@PathVariable(value = "typeId") Long typeId) throws ResourceNotFoundException {
//        try {
//            Type type = typeService.findTypeById(typeId);
//            List<Property> propertyDTOs = propertyRepository.findPropertyById(Optional.ofNullable(type));
//            return new ResponseEntity<>(propertyDTOs, HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }




    @GetMapping("/{propertyId}")
    public ResponseEntity<Object> getPropertyById(@PathVariable(value = "propertyId") Long propertyId) throws ResourceNotFoundException {
        try {
            PropertyDTO propertyDTO = propertyServiceInterface.findPropertyById(propertyId);
            return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/checkProperty")
    public ResponseEntity<Boolean> existsByPropertyId(@RequestParam Long userId){
        try {
            Boolean properties = propertyServiceInterface.existByUserId(userId);
            return new ResponseEntity<>(properties, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProperty(@PathVariable Long id) throws ResourceNotFoundException {
        try {
//            PropertyDTO propertyDTO = propertyServiceInterface.findById(id);
          HashMap<String, Boolean> property = propertyServiceInterface.deleteProperty(id);
            return new ResponseEntity<>(property,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    @DeleteMapping("/prop/{typesId}")
    public ResponseEntity<List<Property>> deleteAllPropertiesOfType(@PathVariable(value = "typesId") Long typesId) {
        if (!propertyRepository.existsById(typesId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + typesId);
        }

        propertyRepository.deleteByTypesId(Math.toIntExact(typesId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/propert/{categoriesId}")
    public ResponseEntity<List<Property>> deleteAllPropertiesOfCategory(@PathVariable(value = "categoriesId") Long categoriesId) {
        if (!propertyRepository.existsById(categoriesId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + categoriesId);
        }

        propertyRepository.deleteByCategoriesId(Math.toIntExact(categoriesId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/allProperties")
    public ResponseEntity<HttpStatus> deleteAllProperties() {
        propertyRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}