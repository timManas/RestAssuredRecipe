package org.example;

import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameTest extends VideoGameConfig {

    // Notice this does not have the .log().all() since we added a filter in the VideoGameConfig
    @Test
    public void getAllGames() {
        given()
        .when()
        .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void createNewGamebyJSON() {

    }
 }
