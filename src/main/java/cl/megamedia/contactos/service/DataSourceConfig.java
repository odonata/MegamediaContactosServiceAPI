package cl.megamedia.contactos.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

/**
 * Clase de configuracion del Datasource de spring
 * @author Gonzalo Silva
 *
 */
@Configuration
public class DataSourceConfig {



	@Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
	
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}