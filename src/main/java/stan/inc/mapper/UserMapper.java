package stan.inc.mapper;

import org.mapstruct.Mapper;
import stan.inc.DTO.UserDTO;
import stan.inc.models.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDto(User user);

}
