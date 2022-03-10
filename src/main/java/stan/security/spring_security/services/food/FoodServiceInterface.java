package stan.security.spring_security.services.food;

import stan.security.spring_security.DTO.FoodDTO;

import java.util.List;

/**
 * Author::Stanley
 * Since
 * Version 1.0.0
 */
public interface FoodServiceInterface {

    public FoodDTO createNewFood(long staffId,  FoodDTO foodDTO);

    public List<FoodDTO> findFoodByStoreId(long storeId);




}
