package stan.security.spring_security.DTO;

import lombok.Data;

@Data
public class UserDTO {
  private Long id;
  private String fullName;
  private String email;
  private String username;
  private String password;
}
