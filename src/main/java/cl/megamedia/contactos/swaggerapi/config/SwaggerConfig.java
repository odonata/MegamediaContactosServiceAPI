package cl.megamedia.contactos.swaggerapi.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cl.megamedia.contactos.swaggerapi.security.ApiKeyFilter;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer{
	

    @Value("${api.key}")
    private String apiKey;

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cl.megamedia.contactos.swaggerapi.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .globalOperationParameters(Collections.singletonList(
                    new ParameterBuilder()
                        .name("X-API-KEY")
                        .description("API Key for accessing the API")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .build()
                ));
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "MegaMedia Contactos Service API",
                "Descripción: Servicios API REST para el Sistema Megamedia Contactos",
                "1.0",
                "https://github.com/odonata?tab=repositories",
                new Contact("Gonzalo Silva", "https://github.com/odonata?tab=repositories", "gonzalo.silva.pereira@gmail.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiKeyFilter());
    }
    
}	