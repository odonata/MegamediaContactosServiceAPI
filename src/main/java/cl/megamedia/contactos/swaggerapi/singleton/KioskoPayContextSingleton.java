package cl.megamedia.contactos.swaggerapi.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cl.megamedia.contactos.swaggerapi.MegaMediaContactosService;

public class KioskoPayContextSingleton {
	
	  static final Logger logger = LoggerFactory.getLogger(KioskoPayContextSingleton.class);


	private static 		ConfigurableApplicationContext context;
	
	public static synchronized ConfigurableApplicationContext getInstance( ) {
        if (context == null){
        	String[] args = new String[0];
        	context=SpringApplication.run(MegaMediaContactosService.class, args);
        	logger.info("Iniciando el servicio desde el singleton");

        }
        logger.info("entregando el cobntexto");
        return context;
    }
	
	public static void setContextNull() {
		
		context=null;
	}
}
