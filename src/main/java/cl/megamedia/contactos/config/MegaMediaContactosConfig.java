package cl.megamedia.contactos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MegaMediaContactosConfig {
	@Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
	    }

}
