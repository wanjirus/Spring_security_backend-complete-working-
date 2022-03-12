package stan.security.spring_security.mapper;

import org.mapstruct.Mapper;
import stan.security.spring_security.DTO.FoodDTO;
import stan.security.spring_security.models.FoodItem;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    FoodDTO toFoodDTO(FoodItem food);

}
