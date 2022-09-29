package stan.security.spring_security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stan.security.spring_security.DTO.PropertyDTO;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.models.Property;
import stan.security.spring_security.repository.PropertyRepository;
import stan.security.spring_security.services.property.PropertyServiceInterface;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;
    private final PropertyServiceInterface propertyServiceInterface;


    @PostMapping()
    public ResponseEntity<Object> createProperty(@RequestParam Long userId,
                                                 @Valid @RequestBody PropertyDTO propertyDTO) throws ResourceNotFoundException {
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
            property.setDescription(property.getDescription());
            property.setPrice(propertyDTO.getPrice());
            property.setStatus(propertyDTO.getStatus());
            property.setPhoneNumber(propertyDTO.getPhoneNumber());
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

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getPropertyById(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        try {
            PropertyDTO propertyDTO = propertyServiceInterface.findPropertyById(userId);
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



}