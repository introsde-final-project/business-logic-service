package business.server;

import business.ExceptionListener;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by bishruti on 2/3/16.
 */

@ApplicationPath("business")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig () {
        packages("business");
        register(ExceptionListener.class);
    }
}