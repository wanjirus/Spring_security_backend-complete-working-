package stan.security.spring_security.DTO;

import lombok.Data;

@Data
public class UserDTO {
  private Long id;
  private String firstname;
  private String secondname;
  private String email;
  private String username;
  private String password;
}
