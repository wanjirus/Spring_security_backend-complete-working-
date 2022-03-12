package stan.security.spring_security.services.food;

import stan.security.spring_security.DTO.FoodDTO;

import javax.validation.Valid;
import java.util.List;

/**
 * Author::Stanley
 * Since
 * Version 1.0.0
 */
public interface FoodServiceInterface {

//    public FoodDTO createNewFood(long staffId,  FoodDTO foodDTO);
public FoodDTO findFoodById(long storeId);
public FoodDTO createNewFood(long foodId, @Valid FoodDTO foodDTO);

}
