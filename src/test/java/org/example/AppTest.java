package org.example;

import static org.junit.Assert.assertTrue;

import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.Test;
import static io.restassured.RestAssured.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends VideoGameConfig {



    @Test
    public void myFirstTest() {
        given()
                .log().all()
                .when()
                .get("/videogame")
                .then()
                .log().all();

    }

    @Test
    public void testWithEndpoints() {
        given()
                .log().all()
                .when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then()
                .log()
                .all();
    }
}
