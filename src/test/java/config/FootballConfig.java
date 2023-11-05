package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;

import static data.Data.API_TOKEN;

public class FootballConfig {

    private static final String CONTENT_TYPE = "json";
    private static final String HEADER_TYPE = "json";  // This will modify what type of response we get back (json or xml)
//    private static String API_TOKEN = "a303f8170f5143aab552a13f5df58c9a";

    @BeforeClass
    public static void setupBeforeClass() {
        System.out.println("----- Execute BeforeClass Setup ----- ");

        // Specify the request specification
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        RestAssured.requestSpecification = requestSpecBuilder
                .setBaseUri("https://api.football-data.org")
                .setBasePath("/v4")
                .setContentType("application/" + CONTENT_TYPE)
                .addHeader("X-Auth-Token", API_TOKEN)
                .addHeader("X-Response-Control", "minified" ) // Reduces meta data which comes from API
                .addFilter(new RequestLoggingFilter())          // This will always log data into the console ... Add this in
                .addFilter(new ResponseLoggingFilter())
                .build();

        // Specify the response specification. This checks if we have a status code 200
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
        RestAssured.responseSpecification = resSpecBuilder
                .expectStatusCode(200)
                .build();
    }
}
