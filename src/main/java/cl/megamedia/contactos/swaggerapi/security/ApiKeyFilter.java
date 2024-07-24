package cl.megamedia.contactos.swaggerapi.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cl.megamedia.contactos.constantes.Constantes;

/**
 * Interceptoir de ApiKey
 * 
 * @author Gonzalo Silva P.
 *
 */
@Component
public class ApiKeyFilter implements HandlerInterceptor {

	
 
    private String apiKey_megamediacontacto = Constantes.APIKEY;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	String rUri = request.getRequestURI();
    	String apiKey = request.getHeader("X-API-KEY");
    	
        // Excluir las rutas de Swagger UI
    	if (rUri.contains("/swagger-ui.html") || 
    			rUri.contains("/v2/api-docs") || 
    			rUri.contains("/swagger-resources") || 
    			rUri.contains("/estadoDelClima")
    	   )
            return true;
        
    	
        // Comprobar el apikey
        if (apiKey == null || !apiKey.equals(apiKey_megamediacontacto)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}