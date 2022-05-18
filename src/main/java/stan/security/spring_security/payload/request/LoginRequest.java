package stan.security.spring_security.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String email;
    private String username;

    @NotBlank
    private String password;

    public String getEmail() { return email;}
    public void setEmail(String email){this.email=email; }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
