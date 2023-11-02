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
        String gameBodyJSON = "{\n" +
                "  \"category\": \"Platform\",\n" +
                "  \"name\": \"Mario\",\n" +
                "  \"rating\": \"Mature\",\n" +
                "  \"releaseDate\": \"2012-05-04\",\n" +
                "  \"reviewScore\": 85\n" +
                "}";

        given()
            .body(gameBodyJSON)
        .when()
            .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void createNewGamebyXML() {
        String gameBodyXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<VideoGameRequest>\n" +
                "\t<category>Platform</category>\n" +
                "\t<name>Mario</name>\n" +
                "\t<rating>Mature</rating>\n" +
                "\t<releaseDate>2012-05-04</releaseDate>\n" +
                "\t<reviewScore>85</reviewScore>\n" +
                "</VideoGameRequest>";

        given()
            .body(gameBodyXML)
            .contentType("application/xml")         // This overrides the content type defined in the VideoGameConfig !!!
            .accept("application/xml")
        .when()
            .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();


    }
 }
