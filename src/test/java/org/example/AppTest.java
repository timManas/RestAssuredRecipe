package org.example;

import static org.junit.Assert.assertTrue;

import config.TestConfig;
import org.junit.Test;
import static io.restassured.RestAssured.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestConfig {



    @Test
    public void myFirstTest() {
        given()
                .log().all()
                .when()
                .get("/videogame")
                .then()
                .log().all();

    }
}
