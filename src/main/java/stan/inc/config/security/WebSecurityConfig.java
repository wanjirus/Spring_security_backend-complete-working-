package stan.inc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import stan.inc.config.CorsFilter;
import stan.inc.config.security.jwt.AuthEntryPointJwt;
import stan.inc.config.security.jwt.AuthTokenFilter;
import stan.inc.controllers.auth.EmailValidator;
import stan.inc.service.auth.UserDetailsServiceImpl;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Autowired

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


        @Override
        protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        }

        // Exposing a bean of RoleHierarchy
        // Configured role Admin to include the role Staff, which in turn includes the role User.
        @Bean
        public RoleHierarchy roleHierarchy() {
            RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
            String hierarchy = "ROLE_ADMIN > ROLE_STAFF \n ROLE_STAFF > ROLE_USER";
            roleHierarchy.setHierarchy(hierarchy);
            return  roleHierarchy;
        }

        // Include this Role Hierarchy in Spring Web Expressions,
        // We add the roleHierarchy instance to the WebSecurityExpressionsHandler.
        // Role hierarchies are a great way ton reduce the number of roles and authorities we need to add a user.
        @Bean
        public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
            DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
            expressionHandler.setRoleHierarchy(roleHierarchy());
            return  expressionHandler;
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
//        return converter;
//    }

        @Bean // Password Encoder uses the BCrypt strong hashing function.
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


    @Bean // Password Encoder uses the BCrypt strong hashing function.
    public EmailValidator emailValidator() {
        return new EmailValidator();
    }


    @Bean
    @Primary
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/v3/api-docs**",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().configurationSource(corsConfigurationSource())
                    .and().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    .authorizeRequests()
                    .antMatchers("/swagger-ui.html",
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/swagger-resources/**",
                            "/configuration/security",
                            "/configuration/ui",
                            "/webjars/**")
                    .permitAll()
                    .antMatchers("/api/v1/**").permitAll()
                    .anyRequest().authenticated()
                    .and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);

        }


        private CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList(
                    "http://localhost:3000",
                    "localhost:3000"
            ));
            configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/api/v1/**", configuration);
            return source;
        }

    }
