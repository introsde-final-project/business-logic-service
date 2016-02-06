package business.server.endpoint;

import business.server.model.Activity;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by bishruti on 2/5/16.
 */
public class ActivityImplementation {
    ActivityImplementation activity = new ActivityImplementation();
    private static ClientConfig clientConfig = new ClientConfig();
    private static Client client = ClientBuilder.newClient(clientConfig);
    private static WebTarget service = client.target(getBaseURI());
    private static Response response;

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://127.0.1.1:8004/storage/activity").build();
    }

     /* Request to obtain all the activities and their details in the list.
       Expected Input: -
       Expected Output: List of activities (String) */

    public static List<Activity> getActivityList() throws Exception {
        response = service.request().accept(MediaType.APPLICATION_JSON).get();
        List<Activity> activity = response.readEntity(List.class);
        return activity;
    }

    /* Request to obtain an activity and the details associated to that activity from the list.
       Expected Input: activityId (Integer)
       Expected Output: Activity and the details associated to that activity. (String) */

    public static Activity getActivityById(Integer activityId) throws Exception {
        response = service.path(String.valueOf(activityId)).request().accept(MediaType.APPLICATION_JSON).get();
        Activity activity = response.readEntity(Activity.class);
        return activity;
    }

    /* Request to obtain an activity and the details associated to that activity from the list by activityName.
       Expected Input: activityName (String)
       Expected Output: Activity and the details associated to that activity. (String) */

    public static Activity getActivityByName(String activityName) throws Exception {
        response = service.path("name/" + activityName).request().accept(MediaType.APPLICATION_JSON).get();
        Activity activity = response.readEntity(Activity.class);
        return activity;
    }
}
