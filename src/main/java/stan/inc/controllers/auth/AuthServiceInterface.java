package stan.inc.controllers.auth;

import org.springframework.http.ResponseEntity;
import stan.inc.payload.request.LoginRequest;
import stan.inc.payload.request.SignupRequest;

public interface AuthServiceInterface {
    String register(SignupRequest signupRequest);
    String confirmToken(String token);
    ResponseEntity<?> login(LoginRequest loginRequest);
}
