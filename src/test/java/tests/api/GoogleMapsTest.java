package tests.api;


import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import tests.TestBase;
import utilities.PropertyReader;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class GoogleMapsTest {
    @Test(groups = {"API"})
    public void testRestAssuredComplexResponse(){

        baseURI = "https://maps.googleapis.com/maps/api";
        String apiKey = PropertyReader.getProperty("api_key");

        given().
                pathParams("type", "json").
                queryParam("input", "Sorrento Valley, CA ").
                queryParam("inputtype", "textquery").
                queryParam("key", apiKey).
                queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photo").
                when().log().all().
                get("place/findplacefromtext/{type}").
                then().log().all().
                statusCode(200).
                body("candidates[0].formatted_address", is("Sorrento Valley, San Diego, CA, USA")).
                body("candidates[0].geometry.location.lat", is(32.900024F));
    }

    @Test(groups = {"API"})
    public void extractValuesFromResponse(){

        baseURI = "https://maps.googleapis.com/maps/api";
        String apiKey = PropertyReader.getProperty("api_key");      //won't work bcs need api key for google maps


        JsonPath jsonPath =
                given().
                        pathParams("type", "json").
                        queryParam("input", "Sorrento Valley, CA ").
                        queryParam("inputtype", "textquery").
                        queryParam("key", apiKey).
                        when().log().all().
                        get("place/findplacefromtext/{type}").
                        then().log().all().
                        statusCode(200).extract().jsonPath();

        String placeId = jsonPath.getString("candidates[0].place_id");
        System.out.println(placeId);

    }
}
