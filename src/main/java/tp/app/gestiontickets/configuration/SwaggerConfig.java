package tp.app.gestiontickets.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Gestion ticket API")
                                .description("Une application de gestion de tickets")
                                .version("1.0.0")
                                .contact(
                                        new Contact().name("Harouna DIALLO")
                                                .email("harouna.hkdk@gmail.com"))
                );
    }
}
