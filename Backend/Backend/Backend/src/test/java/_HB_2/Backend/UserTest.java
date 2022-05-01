package _HB_2.Backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void userTestsByMatt() {
        //send request and receive response
        Response response = RestAssured.given().
                header("Content-Type", "json").
                header("charset","utf-8").
                body("    \"firstName\": \"a\"," +
                        "    \"lastName\": \"a\"," +
                        "    \"address\": \"a\"," +
                        "    \"city\" : \"a\"," +
                        "    \"state\": \"a\"," +
                        "    \"zip\": \"a\"," +
                        "    \"email\": \"ThisIsMatt'sTest\"," +
                        "    \"phoneNumber\": \"a\"," +
                        "    \"password\": \"a\"").
                when().
                post("/driver/registerDriver");


        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

    }

}

