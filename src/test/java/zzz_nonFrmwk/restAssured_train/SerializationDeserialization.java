package zzz_nonFrmwk.restAssured_train;


import org.testng.annotations.Test;
import tests.TestBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SerializationDeserialization extends TestBase {


    static{
        baseURI = "http://localhost:8080/app";
    }
    int id = 100 + new Random().nextInt(1000);

    @Test(groups = {})
    public void testPostAndSerialize(){

        Map<String, Object> videoGame = new HashMap<>();
        videoGame.put("id", id);
        videoGame.put("releaseDate", "1984-06-25");
        videoGame.put("reviewScore", 88);
        videoGame.put("category", "Puzzle");
        videoGame.put("rating", "Universal");

        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body(videoGame).
                when().log().all().
                post("/videogames").
                then().log().all().
                statusCode(200).
                header("Content-Type", equalTo("application/json")).
                body("status", containsString("Record Added Successfully"));
    }

    @Test(groups = {})
    public void testSerializeUsingPOJO(){

        VideoGame videoGame = new VideoGame(
                id,
                "Half Life",
                "1999-09-03",
                99,
                "FPS",
                "PG13");

        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body(videoGame).
                when().log().all().
                post("/videogames").
                then().log().all().
                statusCode(200).
                header("Content-Type", equalTo("application/json")).
                body("status", containsString("Record Added Successfully"));
    }



    @Test (groups = {"API"})
    public void deserializeUsingMap() { //this example hs both deserialization and serialization

        VideoGame videoGame = new VideoGame(
                3,
                "Half Life",
                "1999-09-03",
                99,
                "FPS",
                "PG13");

        Map map =
                given().
                        header("Content-Type", "application/json").
                        header("Accept", "application/json").
                        body(videoGame).
                        pathParam("videoGameId", "3").
                        when().log().all().
                        put("/videogames/{videoGameId}").
                        then().log().all().
                        statusCode(200).
                        header("Content-Type", equalTo("application/json")).
                        extract().as(Map.class);


        System.out.println(map);
        System.out.println(map.get("name"));

    }


    @Test (groups = {"API"})
    public void deserializeUsingPOJO() {

        VideoGame videoGame = new VideoGame(
                3,
                "Half Life",
                "1999-09-03",
                99,
                "FPS",
                "PG13");

        VideoGame responseAsVideoGameObject =
                given().
                        header("Content-Type", "application/json").
                        header("Accept", "application/json").
                        body(videoGame).
                        pathParam("videoGameId", "3").
                        when().log().all().
                        put("/videogames/{videoGameId}").
                        then().log().all().
                        statusCode(200).
                        header("Content-Type", equalTo("application/json")).
                        extract().as(VideoGame.class);

        System.out.println(responseAsVideoGameObject);
        System.out.println(responseAsVideoGameObject.getName());

    }

}
