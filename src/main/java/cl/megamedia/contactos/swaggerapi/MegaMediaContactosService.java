package cl.megamedia.contactos.swaggerapi;


import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Inicializacion del servicioo API REST
 * @author Gonzalo Silva P:
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {
	    "cl.megamedia.contactos",
	    "cl.megamedia.contactos.swaggerapi",
	    "cl.megamedia.contactos.security"
	})
public class MegaMediaContactosService {

	private static 				ConfigurableApplicationContext context;
	public static void main(String[] args) throws Exception {
		
		  final Logger logger = LoggerFactory.getLogger(MegaMediaContactosService.class);

		  try {
			  SpringApplication.run(MegaMediaContactosService.class, args);
		  	

            
		  }catch (Exception e) {
			  logger.error("Error al iniciar la aplicación", e);
		}
		


		
	}

	

}
