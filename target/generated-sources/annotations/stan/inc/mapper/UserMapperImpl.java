package stan.inc.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import stan.inc.DTO.UserDTO;
import stan.inc.models.user.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-11T22:40:06+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setFirstname( user.getFirstname() );
        userDTO.setLastname( user.getLastname() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );

        return userDTO;
    }
}
