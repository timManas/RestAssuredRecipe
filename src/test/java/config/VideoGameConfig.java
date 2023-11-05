package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;
import org.junit.BeforeClass;

public class VideoGameConfig {

    private static final String CONTENT_TYPE = "json";
    private static final String HEADER_TYPE = "json";  // This will modify what type of response we get back (json or xml)

    @BeforeClass
    public static void setupBeforeClass() {
        System.out.println("----- Execute BeforeClass Setup ----- ");

        // Specify the request specification
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        RestAssured.requestSpecification =  reqSpecBuilder
                .setBaseUri("https://videogamedb.uk/")
                .setBasePath("api/v2")
                .setPort(443)
                .setContentType("application/" + CONTENT_TYPE)                 // This states what format WE are sending
                .addHeader("Accept", "application/" + HEADER_TYPE)
                .addFilter(new RequestLoggingFilter())          // This will always log data into the console ... Add this in
                .addFilter(new ResponseLoggingFilter())         // Replaces the .log().all()
                .build();

        // Specify the response specification. This checks if we have a status code 200
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
        RestAssured.responseSpecification = resSpecBuilder
                .expectStatusCode(200)
                .build();

    }

    @Before
    public void setupBefore() {
        System.out.println("----- Execute Before Setup ----- ");
    }


}
