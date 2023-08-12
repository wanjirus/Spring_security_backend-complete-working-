package stan.inc.controllers.auth;

import org.springframework.context.annotation.Bean;

import java.util.function.Predicate;

public class EmailValidator implements Predicate<String> {

  @Bean
    public boolean test(String s) {
        //todo regex email validation
        return true;
    }
}
