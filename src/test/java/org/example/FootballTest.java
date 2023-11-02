package org.example;

import config.FootballConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FootballTest extends FootballConfig {

    @Test
    public void getDetailsOfOneAreas() {
        given()
                .queryParam("areas", 2076)
        .when()
                .get("/areas")
        .then();
    }

    @Test
    public void getDetailsOfMultipleAreas() {
        String areas = "2076,2077,2078";

        // Note because we are sending commas in, we need to disable URL encoding

        given()
                .urlEncodingEnabled(false)          // allows us to put commas into query params
                .queryParam("areas", areas)
        .when()
                .get("/areas")
       .then();
    }
}
