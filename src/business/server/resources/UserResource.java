package business.server.resources;

import business.server.endpoint.UserImplementation;
import business.server.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * Created by bishruti on 2/3/16.
 */

@Stateless // Used only if the the application is deployed in a Java EE container
@LocalBean // Used only if the the application is deployed in a Java EE container
@Path("/user")
public class UserResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    /*  Request to obtain all the users and their details in the list.
        Expected Input: -
        Expected Output: List of users (String) */

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getListOfUser() throws Exception {
        List<User> userList = UserImplementation.getListOfUser();
        if (userList != null) {
            System.out.println("Generating the list of users...");
            return Response.ok(userList).build();
        }
        else {
            return Response.status(404).build();
        }
    }

     /* Request to obtain a user and the details associated to that user from the list.
       Expected Input: uId (Integer)
       Expected Output: User and the details associated to that user. (String) */

    @Path("{uId}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getUserDetail(@PathParam("uId") int uId) throws Exception {
        User user = User.getUserDetail(uId);
        if (user != null) {
            System.out.println("Getting the info of the user with id: " + uId + "...");
            return Response.ok(user).build();
        }
        else {
            return Response.status(404).build();
        }

    }

    /* Navigates to HealthMeasureHistoryResource if both uId and measuretype is obtained */
    @Path("{uId}/{measuretype}")
    public HealthMeasureHistoryResource getUserHistory(@PathParam("uId") int uId, @PathParam("measuretype") String measuretype) {
        return new HealthMeasureHistoryResource(uriInfo, request, uId, measuretype);
    }
}
