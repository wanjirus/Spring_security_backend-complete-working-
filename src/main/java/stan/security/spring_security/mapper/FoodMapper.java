package stan.security.spring_security.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import stan.security.spring_security.DTO.FoodDTO;
import stan.security.spring_security.models.FoodItem;

import java.util.List;

@Mapper(componentModel = "spring",  uses = StoreMapper.class)
@Transactional
public interface FoodMapper {
    FoodDTO toFoodDTO(FoodItem food);

    List<FoodDTO> toJobDtoList(List<FoodItem> jobs);
}
