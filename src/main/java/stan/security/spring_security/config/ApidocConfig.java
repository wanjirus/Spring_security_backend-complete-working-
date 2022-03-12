//package stan.security.spring_security.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.swagger.v3.core.jackson.ModelResolver;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ApidocConfig {
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("SunAppointment application API")
//                        .version("1.0.0")
//                        .description("Appointments as a Service Information System")
//                        .contact(new Contact().email("cto@sunflashtech.co.ke").name("SunApp Developers"))
//                        .termsOfService("http://www.sunflashtech.co.ke/terms/")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//    }
//    @Bean
//    public ModelResolver modelResolver(ObjectMapper objectMapper) {
//        return new ModelResolver(objectMapper);
//    }
//
//}
