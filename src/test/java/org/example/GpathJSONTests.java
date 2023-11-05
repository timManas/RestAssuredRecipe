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

    // Find a specifi element  with id = 4832
    @Test
    public void extractSingleValueWithFind() {
        Response response = get("teams/57");
        String certainPlayer = response.path("squad.find { it.id == 4832 }.name");
        System.out.println("Player: " + certainPlayer);
    }

    // Find All elements with id bigger than 7784
    @Test
    public void extractListOfValuesWithFindAll() {
        Response response = get("teams/57");
        List<String> playNames = response.path("squad.findAll { it.id >= 7784 }.name");
        System.out.println("playNames: " + playNames);
    }

    // Finds the player with biggest id
    @Test
    public void extractSingleValueWithHighestNum() {
        Response response = get("teams/57");
        String playerName = response.path("squad.max { it.id }.name");
        System.out.println("Highest Id: " + playerName);
    }

    // Finds the player with smallest id
    @Test
    public void extractSingleValueWithLowestNum() {
        Response response = get("teams/57");
        String playerName = response.path("squad.min { it.id }.name");
        System.out.println("Lowest Id: " + playerName);
    }

    // Grab all ideas and Add them all together
    @Test
    public void extractMultipleValuesAndSumThem() {
        Response response = get("teams/57");
        int sumOfIds = response.path("squad.collect { it.id }.sum()");
        System.out.println("sumOfIds: " + sumOfIds);
    }


}
