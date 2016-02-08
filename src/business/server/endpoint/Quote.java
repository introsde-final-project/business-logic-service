package business.server.endpoint;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by bishruti on 2/3/16.
 */
public class Quote {
    Quote quote = new Quote();
    private static WebTarget service;
    private static Response response;
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://storage-service.herokuapp.com/storage/quote").build();
    }

     /*  Request to obtain quote.
       Expected Input: -
       Expected Output: Quote (String) */

    public static String getQuote() {
        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        service = client.target(getBaseURI());
        response = service.request().accept(MediaType.APPLICATION_JSON).get();
        String quote = response.readEntity(String.class);
        JSONObject jsonObj = new JSONObject(quote);
        String quotation = jsonObj.get("quote").toString();
        return quotation;
    }
}