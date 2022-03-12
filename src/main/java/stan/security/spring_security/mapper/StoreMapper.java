package stan.security.spring_security.mapper;
//
//import org.mapstruct.Mapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import stan.security.spring_security.DTO.StoreDTO;
//import stan.security.spring_security.models.Store;
//
//import java.util.List;
//@Mapper(componentModel = "spring", uses = UserMapper.class)
//@Component
//public interface StoreMapper {
//    StoreDTO toStoreDto(Store store);
//}
import org.mapstruct.Mapper;
import stan.security.spring_security.models.Store;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store toStoreDto(Store store);
}
