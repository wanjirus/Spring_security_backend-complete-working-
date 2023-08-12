package stan.inc.controllers.property;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stan.inc.DTO.MsgsDTO;
import stan.inc.exceptions.ResourceNotFoundException;
import stan.inc.service.property.PropertyMsgsInterface;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property/propertymessages")
public class PropertyMessageController {
    @Autowired
    PropertyMsgsInterface propertyMsgsInterface;

    @PostMapping("/create")
    public ResponseEntity<Object> createPropertyMessage(@RequestParam Long propertyId, @Valid @RequestBody MsgsDTO msgsDTO)
            throws ResourceNotFoundException {
        try {
            MsgsDTO returnedPropertyDTO = propertyMsgsInterface.createPropertyMessage(propertyId,msgsDTO);
            return new ResponseEntity<>(returnedPropertyDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity <Object> getAllProperties() {
        try {
            List<MsgsDTO> msgsDTOS = propertyMsgsInterface.findAll();
            return new ResponseEntity<>(msgsDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.valueOf(e.getMessage()) );
        }
    }
    @GetMapping("/{propertyId}")
    public ResponseEntity<Object> getPropertyMessages(@PathVariable Long propertyId){
        try {
            List<MsgsDTO> msgsDTOS = propertyMsgsInterface.findPropertyMessageByPropertyId(propertyId);
            return new ResponseEntity<>(msgsDTOS, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.valueOf(e.getMessage()));
        }

    }
}
