package stan.security.spring_security.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stan.security.spring_security.DTO.FoodDTO;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.mapper.FoodMapper;
import stan.security.spring_security.repository.FoodItemRepository;
import stan.security.spring_security.services.food.FoodService;
import stan.security.spring_security.services.food.FoodServiceInterface;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/foodItem")
public class FoodItemController {
    private final FoodServiceInterface foodServiceInterface;
    // Api to Create a food and food Details API
    @PostMapping("/{storeId}")
    public ResponseEntity<Object> createFood(@PathVariable(value = "storeId") Long foodId, @Valid @RequestBody FoodDTO foodDTO) throws
            ResourceNotFoundException {
        try {
            FoodDTO returnedFoodDTO =foodServiceInterface.createNewFood(foodId,foodDTO);
            return  new ResponseEntity<>(returnedFoodDTO,HttpStatus.CREATED);

        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

