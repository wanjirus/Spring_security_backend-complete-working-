package stan.inc.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import stan.inc.controllers.auth.token.ConfirmationToken;
import stan.inc.controllers.auth.token.ConfirmationTokenService;
import stan.inc.models.user.User;
import stan.inc.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository appUserRepository;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Autowired
   BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        try{
            final User appUser = appUserRepository.findUserByEmail(email);
            if (appUser == null){
                throw  new UsernameNotFoundException( "No user found with username: " + email);
            }

            List<GrantedAuthority> authorities = appUser.getAuthorities().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                    .collect(Collectors.toList());


            return new org.springframework.security.core.userdetails.User(

                    appUser.getEmail(),
                    appUser.getPassword() ,
                    appUser.isEnabled(),
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    authorities

            );
        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }



    public String singUpUser(User appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();
        if (userExists) {


            //TODO: if SAME USER TRYING TO REGISTER AND EMAIL NOT CONFIRMED SEND ANOTHER CONFIRMATION EMAIL
            //TODO:
            throw new IllegalStateException("Email Already Taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);


        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;

//todo send email
    }

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }



    private boolean emailExist(String email) {
        return appUserRepository.findByEmail(email).isPresent();
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(auth.getDefaultUserDetailsService());
    }
}
