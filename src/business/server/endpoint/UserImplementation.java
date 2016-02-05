package business.server.endpoint;

import business.server.model.HealthMeasureHistory;
import business.server.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by bishruti on 2/3/16.
 */
public class UserImplementation {
    UserImplementation user = new UserImplementation();
    private static ClientConfig clientConfig = new ClientConfig();
    private static Client client = ClientBuilder.newClient(clientConfig);
    private static WebTarget service = client.target(getBaseURI());
    private static Response response;
    private static String uId;
    private static ObjectMapper userMapper = new ObjectMapper();

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://127.0.1.1:8004/storage/user").build();
    }

    /*  Request to obtain all the users and their details in the list.
        Expected Input: -
        Expected Output: List of users (String) */

    public static List<User> getListOfUser() throws IOException {
        response = service.request().accept(MediaType.APPLICATION_JSON).get();
        List<User> user = response.readEntity(List.class);
        return user;
    }

    /* Request to obtain a user and the details associated to that user from the list.
       Expected Input: uId (Integer)
       Expected Output: User and the details associated to that user. (String) */

    public static User getUserDetail(int uId) throws IOException {
        response = service.path(String.valueOf(uId)).request().accept(MediaType.APPLICATION_JSON).get();
        User user = response.readEntity(User.class);
        return user;
    }

    /* Request to obtain all measure details about a measure of a user in the list.
        Expected Input: uId (Integer)
                       measureType (String)
       Expected Output: List of details of measure types. (String) */

    public static List<HealthMeasureHistory> getUserHistory(int uId, String measureType) throws Exception {
        response = service.path(String.valueOf(uId) + "/"  + measureType).request().accept(MediaType.APPLICATION_JSON).get();
        List<HealthMeasureHistory> healthMeasureHistory = response.readEntity(List.class);
        return healthMeasureHistory;
    }

     /*  Request to obtain measure details about a particular measure of a user in the list.
        Expected Input: uId (Integer)
                        measureType (String)
                        hmhId (Integer)
        Expected Output: Details of a particular measure. (String) */

    public static List<HealthMeasureHistory> getUserMeasure(int uId, String measureType, int hmhId) throws Exception {
        response = service.path(String.valueOf(uId) + "/"  + measureType + "/" + String.valueOf(hmhId)).request().accept(MediaType.APPLICATION_JSON).get();
        List<HealthMeasureHistory> healthMeasureHistory = response.readEntity(List.class);
        return healthMeasureHistory;
    }

}
