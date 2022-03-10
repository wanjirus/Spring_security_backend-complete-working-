package com.sunflash.sunappointment.mapper;

import com.sunflash.sunappointment.entities.auth.Users;
import com.sunflash.sunappointment.model.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserDTO> toUserDtoList(List<Users> users);

    default UserDTO toUserDTO(Users users){
        UserDTO userInfoDTO = new UserDTO();

        userInfoDTO.setId(users.getId());
        userInfoDTO.setFullName(users.getFirstname() + " " + users.getLastname());
        userInfoDTO.setEmail(users.getEmail());
        userInfoDTO.setPhoneNumber(users.getPhoneNumber());
        userInfoDTO.setUsername(users.getUsername());
        userInfoDTO.setPassword(users.getPassword());

        return userInfoDTO;
    }
}
