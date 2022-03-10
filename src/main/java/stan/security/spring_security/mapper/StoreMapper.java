package stan.security.spring_security.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import stan.security.spring_security.DTO.StoreDTO;
import stan.security.spring_security.models.Store;

import java.util.List;
@Mapper(componentModel = "spring")
public abstract class StoreMapper {
    public abstract StoreDTO toStoreDto(Store store);
    abstract List<StoreDTO> toStoreDtoList(List<Store> stores);
}
