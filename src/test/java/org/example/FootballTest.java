package org.example;

import config.FootballConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

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

    // URL  - https://api.football-data.org/v4/teams/57
    // Change the value to 1886 and it will fail
    // Expected: <1887>
    // Actual: <1886>
    @Test
    public void getDateFounded() {
        given()
        .when()
            .get("teams/57")
        .then()
            .body("founded", equalTo(1886));        // Check key "founded" contains value 1886
    }

    // https://api.football-data.org/v4/competitions/2021/teams
    @Test
    public void validateFirstTeamName() {
        given()
        .when()
           .get("competitions/2021/teams")
        .then()
           .body("teams.name[0]", equalTo("Arsenal FC"));
    }


    @Test
    public void extractDatafromResponse() {
        String responseBody = get("teams/57").asString();
        System.out.println("ResponseBody: " + responseBody);
    }


    @Test
    public void extractAllTeamData_ThenValidate() {
        Response response = given()
                .when()
                    .get("teams/57")
                .then()
                    .contentType(ContentType.JSON)
                    .extract().response();          // This extracts the response body

        System.out.println("response: " + response.asString());
    }

    @Test
    public void extractHeaders() {
        Response response = given()
                .when()
                .get("teams/57")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        // Grab Headers
        String headers = String.valueOf(response.getHeaders());
        String header = response.getHeader("X-API-Version");
        String contentTypeHeader = response.getContentType();
        int statusCode = response.getStatusCode();


        System.out.println("headers: " + headers);
        System.out.println("header: " + header);
        System.out.println("contentTypeHeader: " + contentTypeHeader);
        System.out.println("statusCode: " + statusCode);

    }

    // Fetch the first team name response using jsonPath
    @Test
    public void extractFirstTeamNameUsingJsonPath() {
        String firstTeamName = get("competitions/2021/teams").jsonPath().getString("teams.name[0]");
        String secondTeamName = get("competitions/2021/teams").jsonPath().getString("teams.name[1]");

        System.out.println("firstTeamName: " + firstTeamName);
        System.out.println("secondTeamName: " + secondTeamName);

    }


    @Test
    public void extractAllTeamNames() {
        // Get Response first
        Response response = get("competitions/2021/teams").then()
                .extract().response();

        // Extract team names
        List<String> teamNames = response.path("teams.name");           // this is equivalent to jsonPath
        System.out.println("teamNames: " + teamNames);
    }
}
