package stan.security.spring_security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stan.security.spring_security.DTO.StoreDTO;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.services.store.StoreServiceInterface;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")

public class StoreController {

    @Autowired
    private final StoreServiceInterface storeServiceInterface;


    @PostMapping("/{userId}")
    public ResponseEntity<Object> createStore(@PathVariable(value = "userId") Long userId,
                                              @Valid @RequestBody StoreDTO storeDTO) throws ResourceNotFoundException {
        try {
            StoreDTO returnedStoreDTO=storeServiceInterface.createNewStore(userId,storeDTO);
            return new ResponseEntity<>(returnedStoreDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getStoreById(@PathVariable(value = "userId") Long userId) {
        try {
            StoreDTO storeDTO=storeServiceInterface.findStoreById(userId);
            return new ResponseEntity<>(storeDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}