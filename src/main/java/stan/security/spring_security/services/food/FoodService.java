package stan.security.spring_security.services.food;
/**
 * Author::Stanley
 * Since
 * Version 1.0.0
 */

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stan.security.spring_security.DTO.FoodDTO;
import stan.security.spring_security.DTO.StoreDTO;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.mapper.FoodMapper;
import stan.security.spring_security.models.FoodItem;
import stan.security.spring_security.repository.FoodItemRepository;
import stan.security.spring_security.services.store.StoreService;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodService implements FoodServiceInterface {
private  final FoodItemRepository foodRepository;

private final FoodMapper foodMapper;
private  final StoreService storeService;

public FoodDTO createNewFood(long storeId, FoodDTO foodDTO) throws ResourceNotFoundException{
    StoreDTO store = storeService.findStoreById(storeId);
    FoodItem foodItem = constructFoodObject(foodDTO);
    foodItem.setStore(store);
    foodItem= foodRepository.save(foodItem);
    return  foodMapper.toFoodDTO(foodItem);
}

    @Override
    public List<FoodDTO> findFoodByStoreId(long storeId) {
        return null;
    }


private FoodItem constructFoodObject(FoodDTO foodDTO){
    FoodItem foodItem = FoodItem.builder()
            .foodname(foodDTO.getFoodName())
            .location(foodDTO.getLocation())
            .quantity(foodDTO.getQuantity())
            .price(foodDTO.getPrice())
            .build();
    return  foodItem;

}

}