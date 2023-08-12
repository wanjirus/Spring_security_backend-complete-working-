package stan.inc.service.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stan.inc.exceptions.ResourceNotFoundException;
import stan.inc.models.user.User;
import stan.inc.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    // Get All Users
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Find User By id.
    @Transactional
    public User findUserById(long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No user record Found::"+id));
    }

    // Check if user exists by email.
    @Transactional
    public Boolean checkUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Create a new user.
//    @Transactional
//    public Users createNewUser(@Valid RegisterDTO registerDTO) {
//        // Creating a new user's account.
//        Users users = new Users();
//
//        users.setFirstname(registerDTO.getFirstname());
//        users.setLastname(registerDTO.getLastname());
//        users.setEmail(registerDTO.getEmail());
//        users.setPhoneNumber(registerDTO.getPhoneNumber());
//        users.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
//
//        // Assuming that a standard user being registered, we're assigning it the ROLE_USER role.
//        Set <Role> roles = new HashSet<>(Arrays.asList(roleService.getRoleByName("ROLE_USER")));
//
//        return userRepository.save(users);
//
//    }

    // Save User Details.
    @Transactional
    public User saveUserDetails(@Valid User users) {
        return userRepository.save(users);
    }

    // Delete a user.
    @Transactional
    public void deleteUserDetails(@Valid User users) {
        userRepository.delete(users);
    }
}