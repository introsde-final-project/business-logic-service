package business.server.endpoint;

import business.server.model.Goal;
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
public class GoalImplementation {
    GoalImplementation goal = new GoalImplementation();
    private static ClientConfig clientConfig = new ClientConfig();
    private static Client client = ClientBuilder.newClient(clientConfig);
    private static WebTarget service = client.target(getBaseURI());
    private static Response response;

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://127.0.1.1:8004/storage/goal").build();
    }

     /*  Request to obtain all the goals and their details in the list.
        Expected Input: -
        Expected Output: List of goals (List) */

    public static List<Goal> getGoalList() throws Exception {;
        response = service.request().accept(MediaType.APPLICATION_JSON).get();
        List<Goal> goal = response.readEntity(List.class);
        return goal;
    }

    /* Request to obtain a goal and the details associated to that goal from the list.
       Expected Input: goalId (Integer)
       Expected Output: Goal and the details associated to that goal. (Object) */

    public static Goal getGoalById(Integer goalId) throws Exception {
        response = service.path(String.valueOf(goalId)).request().accept(MediaType.APPLICATION_JSON).get();
        Goal goal = response.readEntity(Goal.class);
        return goal;
    }

    /* Request to obtain a goal and the details associated to that goal from the list by goalName.
        Expected Input: goalName (String)
        Expected Output: Goal and the details associated to that goal. (Object) */

    public static Goal getGoalByName(String goalName) throws Exception {
        response = service.path("name/" + goalName).request().accept(MediaType.APPLICATION_JSON).get();
        Goal goal = response.readEntity(Goal.class);
        return goal;
    }

}
