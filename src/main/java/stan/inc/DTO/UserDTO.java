package stan.inc.DTO;

import lombok.Data;
import stan.inc.models.user.Role;

import java.util.Set;

@Data
public class UserDTO {
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private String username;
  private String password;
  private Set<Role> role;
}
