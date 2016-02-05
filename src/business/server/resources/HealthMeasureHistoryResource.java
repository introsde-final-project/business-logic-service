package business.server.resources;

import business.server.endpoint.UserImplementation;
import business.server.model.HealthMeasureHistory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * Created by bishruti on 2/5/16.
 */
public class HealthMeasureHistoryResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    int uId;
    String measuretype;

    public HealthMeasureHistoryResource(UriInfo uriInfo, Request request, int uId, String measuretype) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.uId = uId;
        this.measuretype = measuretype;
    }

    /* Request to obtain all measure details about a measure of a user in the list.
        Expected Input: uId (Integer)
                       measureType (String)
       Expected Output: List of details of measure types. (String) */

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserHistory() throws Exception {
        List<HealthMeasureHistory> userHistoryList = UserImplementation.getUserHistory(uId, measuretype);
        if (userHistoryList != null) {
            System.out.println("Generating list of Health Measure History...");
            return Response.ok(userHistoryList).build();
        }
        else {
            return Response.status(404).build();
        }
    }

     /*  Request to obtain measure details about a particular measure of a user in the list.
        Expected Input: uId (Integer)
                        measureType (String)
                        hmhId (Integer)
        Expected Output: Details of a particular measure. (String) */

    @Path("/{hmhId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserMeasure(@PathParam("hmhId") int hmhId) throws Exception {
        List<HealthMeasureHistory> userHistory = UserImplementation.getUserMeasure(uId, measuretype, hmhId);
        if (userHistory != null) {
            System.out.println("Getting details of Health Measure History");
            return Response.ok(userHistory).build();
        }
        else {
            return Response.status(404).build();
        }
    }
}
