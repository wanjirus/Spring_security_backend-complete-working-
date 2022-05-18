package stan.security.spring_security.mapper;

import org.mapstruct.Mapper;
import stan.security.spring_security.DTO.UserDTO;
import stan.security.spring_security.models.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserDTO> toUserDtoList(List<User> users);

    default UserDTO toUserDTO(User users){
        UserDTO userInfoDTO = new UserDTO();

        userInfoDTO.setId(users.getId());
        userInfoDTO.setFirstname(users.getFirstname());
        userInfoDTO.setSecondName(users.getSecondname());
        userInfoDTO.setEmail(users.getEmail());
        userInfoDTO.setUsername(users.getUsername());
        userInfoDTO.setPassword(users.getPassword());

        return userInfoDTO;
    }
}
