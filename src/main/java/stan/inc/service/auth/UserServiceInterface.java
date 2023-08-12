package stan.inc.service.auth;

import stan.inc.exceptions.ResourceNotFoundException;
import stan.inc.models.user.User;

public interface UserServiceInterface {

     abstract User findUserById(long id) throws ResourceNotFoundException;
}
