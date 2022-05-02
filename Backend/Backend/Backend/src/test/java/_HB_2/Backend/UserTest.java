package _HB_2.Backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import _HB_2.Backend.driver.Driver;
import _HB_2.Backend.driver.DriverRepository;
import _HB_2.Backend.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DriverRepository driverRepository;

    private int driverId;

    private User user;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void createUser() {
        //send request and receive response
        Driver driver = new Driver("FirstNameTest",
                                    "LastNameTest",
                                    "AddressTest",
                                    "CityTest",
                                    "StateTest",
                                    "ZipTest",
                                    "EmailTest",
                                    "PhoneNumberTest",
                                    "PasswordTest");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/driver/registerDriver", driver, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());

        user = driverRepository.findByEmail("EmailTest");
        driverId = user.getId();

    }

    @Test
    public void deleteUser() {
        //send request and receive response
        String string = "/user/deleteUser?id=" + driverId;
        Response response = RestAssured.given().
                when().
                delete(string);

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

}

