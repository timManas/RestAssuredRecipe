package config;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestConfig {

    @BeforeClass
    public static void setupBeforeClass() {
        System.out.println("----- Execute BeforeClass Setup ----- ");
        RestAssured.baseURI = "https://videogamedb.uk/";         // Set base URI
        RestAssured.basePath = "api/v2";                                      // Set base path ... This is /api/v2
        RestAssured.port = 443;                     // Set base Port
    }

    @Before
    public void setupBefore() {
        System.out.println("----- Execute Before Setup ----- ");
        RestAssured.baseURI = "https://videogamedb.uk/";         // Set base URI
        RestAssured.basePath = "api/v2";                                      // Set base path ... This is /api/v2
        RestAssured.port = 443;                     // Set base Port
    }
}
