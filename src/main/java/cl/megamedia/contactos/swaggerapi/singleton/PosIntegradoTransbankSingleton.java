package cl.megamedia.contactos.swaggerapi.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

import cl.megamedia.contactos.swaggerapi.MegaMediaContactosService;
import cl.transbank.pos.POSIntegrado;


public class PosIntegradoTransbankSingleton {
	
	  static final Logger logger = LoggerFactory.getLogger(PosIntegradoTransbankSingleton.class);



	private POSIntegrado 						 pos ;
	private String 		 						 puerto;
	private boolean		 						 conectado;
	private static PosIntegradoTransbankSingleton posIntegrado;
	
	   public PosIntegradoTransbankSingleton() {
		   this.pos=new POSIntegrado();

	   }
	
	   public static synchronized PosIntegradoTransbankSingleton getSingletonInstance( ) {
	        if (posIntegrado == null){
	        	posIntegrado = new PosIntegradoTransbankSingleton();

	        }
	        else{
	            logger.info("PosIntegrado ya Instanciado");
	        }
	        return posIntegrado;
	    }
	
	   public POSIntegrado getPosintegrado() {
		   return this.pos;
	   }

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	   
	public void resetPosSingleton() {
		PosIntegradoTransbankSingleton.posIntegrado =null;
		this.puerto=null;
		this.conectado=false;
	}


	
}
