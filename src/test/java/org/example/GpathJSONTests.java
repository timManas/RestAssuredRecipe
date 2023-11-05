package org.example;

import config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GpathJSONTests extends FootballConfig {

    @Test
    public void extractMapOfElementsWithFind() {
        Response response = get("competitions/2021/teams");
        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Manchester United FC'}");
        System.out.println("Map of team Data: " + allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind() {
        Response response = get("teams/57");
        String certainPlayer = response.path("squad.find { it.id == 4832 }.name");
        System.out.println("Player: " + certainPlayer);
    }

    @Test
    public void extractListOfValuesWithFindAll() {
        Response response = get("teams/57");
        List<String> playNames = response.path("squad.findAll { it.id >= 7784 }.name");
        System.out.println("playNames: " + playNames);
    }
}
