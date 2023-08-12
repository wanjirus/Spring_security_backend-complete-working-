package stan.inc.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import stan.inc.exceptions.ResourceNotFoundException;
import stan.inc.models.user.User;
import stan.inc.payload.request.LoginRequest;
import stan.inc.payload.request.SignupRequest;
import stan.inc.repository.RoleRepository;
import stan.inc.repository.UserRepository;
import stan.inc.config.security.jwt.JwtUtils;
import stan.inc.service.auth.UserDetailsServiceImpl;
import stan.inc.service.auth.UserServiceInterface;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsServiceImpl UserDetailsImpl;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RegistrationService registrationService;

    private UserServiceInterface userServiceInterface;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return registrationService.login(loginRequest);
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody SignupRequest request) {

        return registrationService.register(request);

    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);

    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        try {
            User userDto = userServiceInterface.findUserById(id);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}