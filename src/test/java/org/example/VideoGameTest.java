package org.example;

import config.VideoGameConfig;
import config.VideoGameEndpoints;
import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import objects.VideoGame;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameTest extends VideoGameConfig {

    private String gameBodyJSON = "{\n" +
            "  \"category\": \"Platform\",\n" +
            "  \"name\": \"Mario\",\n" +
            "  \"rating\": \"Mature\",\n" +
            "  \"releaseDate\": \"2012-05-04\",\n" +
            "  \"reviewScore\": 85\n" +
            "}";

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

    @Test
    public void updateGame() {
        String id = "3";
        given()
            .body(gameBodyJSON)
        .when()
            .put(VideoGameEndpoints.SINGLE_VIDEO_GAME.replace("{videoGameId}", id))
        .then();
    }

    @Test
    public void deleteGame() {
        String id = "8";
        given()
            .body(gameBodyJSON)
            .accept("text/plain")
        .when()
            .delete(VideoGameEndpoints.SINGLE_VIDEO_GAME.replace("{videoGameId}", id))
        .then();
    }


    @Test
    public void updateGameUsingPathParameter() {
        String id = "3";
        given()
                .body(gameBodyJSON)
                .pathParam("videoGameId", id)           //  Replace videoGameId with "id" from the second argument
        .when()
                .put(VideoGameEndpoints.SINGLE_VIDEO_GAME)
        .then();
    }


    // This is serialization. Take an object and create a JSON representation of it be used
    @Test
    public void testVideoGameSerializationByJson() {
        // Create an object
        VideoGame videoGame = new VideoGame("Shooter", "Virtual Cop", "Mature", "Nov3", 100);

        given()
                .body(videoGame)
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    //https://videogamedb.uk:443/api/v2/videogame/5
    @Test
    public void testVideoGameSchemaXML() {
        given()
                .pathParam("videoGameId", 5)
                .accept("application/xml")
        .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
        .then()
                .body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameXSD.xsd"));
    }
 }
