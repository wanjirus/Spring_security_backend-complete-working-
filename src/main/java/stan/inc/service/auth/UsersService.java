package stan.inc.service.auth;

import stan.inc.exceptions.ResourceNotFoundException;
import stan.inc.models.user.User;
import stan.inc.repository.UserRepository;

public class UsersService implements UserServiceInterface{
    private  UserRepository userRepository;
    //
    public User findUserById(long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Property record found with Id::" + id));
    }

        }

