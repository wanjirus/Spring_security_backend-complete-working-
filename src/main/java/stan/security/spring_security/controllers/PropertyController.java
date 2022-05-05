package stan.security.spring_security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stan.security.spring_security.DTO.PropertyDTO;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.services.property.PropertyServiceInterface;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property")

public class PropertyController {
    private final PropertyServiceInterface propertyServiceInterface;


    @PostMapping("/{userId}")
    public ResponseEntity<Object> createProperty(@PathVariable(value = "userId") Long userId,
                                                 @Valid @RequestBody PropertyDTO propertyDTO) throws ResourceNotFoundException {
        try {
            PropertyDTO returnedPropertyDTO = propertyServiceInterface.createNewProperty(userId,propertyDTO);
            return new ResponseEntity<>(returnedPropertyDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping()
    public ResponseEntity<Object> getPropertyByUserId(@RequestParam Long userId) {
        try {
            List<PropertyDTO> propertyDTOS = propertyServiceInterface.findPropertyByUserId(userId);
            return new ResponseEntity<>(propertyDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getPropertyById(@PathVariable(value = "userId") Long userId) {
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
}