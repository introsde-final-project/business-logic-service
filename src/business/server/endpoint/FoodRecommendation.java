package business.server.endpoint;

import business.server.model.FoodSuggestion;
import org.glassfish.jersey.client.ClientConfig;

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
public class FoodRecommendation {
    FoodRecommendation foodRecommendation = new FoodRecommendation();
    private static WebTarget service;
    private static Response response;

    private static URI getBaseURI(String foodType) {
        return UriBuilder.fromUri(
                //Change here for passing params like low-fat,low-sugar, No-oil-added
                "http://127.0.1.1:8004/storage/foodrecomm?foodType=" + foodType).build();
    }

     /*  Request to obtain Food Recommendation.
       Expected Input: FoodType
       Expected Output: Food Recommendation (Object) */

    public static FoodSuggestion getFoodRecomm(String foodType) {
        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        service = client.target(getBaseURI(foodType));
        response = service.request().accept(MediaType.APPLICATION_JSON).get();
        FoodSuggestion foodRecomm = response.readEntity(FoodSuggestion.class);
        return foodRecomm;
    }
}
