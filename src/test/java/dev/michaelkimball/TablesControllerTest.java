package dev.michaelkimball;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TablesControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/tables")
          .then()
             .statusCode(200);
    }

}