package zzz_nonFrmwk.restAssured_train;




package tests.api;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import tests.TestBase;

import java.util.Random;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;





public class VideoGame_2EndToEndWithinAPI extends TestBase{


    //Testing a series of requests which are common user actions, since some requests can rely on other ones.  We
    // execute a POST request that creates a resource and returns an auto-generated identifier in its response.
    // We then use this identifier to check if this resource is present in the list of elements received by a GET request.
    // Then we PATCH endpoint to update it with new data, and we again invoike a GET request to validate the effect of
    // the patch/update.  Finally we DELETE that resource and use GET to verify the entry no longer exists.



    static{
        baseURI = "http://localhost:8080/app";
    }

//given - request specifications: headers, parameters, authorization, body goes here
//when  - HTTP request (get, post)
//then  - Assertion


    //test End-End flow:
    @Test(groups = {"API"})
    public void testMultiStepFlowWithSeveralEndpoints(){
        int id = 20 + new Random().nextInt(1000);
        //create a new videogame - POST
        //verify creation with GET
        //update entry name with PUT
        //verify update with GET
        //delete entry with DELETE
        //verify entry seized to exist by a GET


//create a new videogame - POST
        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body("{\n" +
                        "  \"id\": "+id+",\n" +
                        "  \"name\": \"string\",\n" +
                        "  \"releaseDate\": \"2022-10-17\",\n" +
                        "  \"reviewScore\": 0,\n" +
                        "  \"category\": \"string\",\n" +
                        "  \"rating\": \"string\"\n" +
                        "}").
                when().log().all().
                post("/videogames").
                then().log().all().
                statusCode(200).
                header("Content-Type", equalTo("application/json")).
                body("status", containsString("Record Added Successfully"));


//verify creation with GET
        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).
                when().log().all().
//                get("/videogames/4"). //hardcoded
        get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().statusCode(200).
                body("id", equalTo(id)).
                time(lessThan(800L));


//update entry name with PUT
        Faker faker = new Faker();
        String newGameName = faker.gameOfThrones().dragon();    //couldn't find game category in faker class so using this

        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body("{\n" +
                        "  \"id\": "+id+",\n" +
                        "  \"name\": \""+newGameName+"\",\n" +
                        "  \"releaseDate\": \"2010-13-09\",\n" +
                        "  \"reviewScore\": 99,\n" +
                        "  \"category\": \"Driving\",\n" +
                        "  \"rating\": \"Universal\"\n" +
                        "}").
                pathParam("videoGameId", id).
                when().log().all().
                put("/videogames/{videoGameId}").
                then().log().all().
                statusCode(200).
                header("Content-Type", equalTo("application/json")).
                body("name", equalTo(newGameName));



//verify update of name with GET
        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).
                when().log().all().
//                get("/videogames/4"). //hardcoded
        get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().statusCode(200).
                body("name", equalTo(newGameName)).
                time(lessThan(800L));


//delete the above updated entry with DELETE
        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).
                when().log().all().
                delete("/videogames/{videoGameId}").
                then().log().all().
                statusCode(200).
                header("Content-Type", equalTo("application/json")).
                body("status", equalTo("Record Deleted Successfully"));



//verify entry seized to exist by a GET
        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).
                when().log().all().
//                get("/videogames/4"). //hardcoded
        get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().statusCode(500).
                body("error", equalTo("Internal Server Error")).
                time(lessThan(800L));

    }
}
