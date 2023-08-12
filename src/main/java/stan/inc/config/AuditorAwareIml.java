package stan.inc.config;


import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareIml implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() { return Optional.of("Admin");}
}