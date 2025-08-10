package tests;

import constants.HttpsMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojoClasses.user.User;
import steps.UserSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;


public class UserAuthorizationTests {

    private User user;
    private String accessToken;
    private final UserSteps userSteps = new UserSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = HttpsMethods.URI;
        user = new User(
                "test-" + System.currentTimeMillis() + "@yandex.ru",
                "password",
                "AuthUser"
        );

        Response registerResponse = userSteps.registerUser(user);

        registerResponse.then()
                .statusCode(200)
                .body("success", equalTo(true));

        accessToken = registerResponse.path("accessToken");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userSteps.deleteUser();
        }
    }

    @Test
    public void successfulLoginTest() {
        Response loginResponse = userSteps.loginUser(
                new User(user.getEmail(), user.getPassword(), null)
        );

        loginResponse.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(user.getEmail()));


        String newAccessToken = loginResponse.path("accessToken");
        String newRefreshToken = loginResponse.path("refreshToken");

        assertNotNull("Access token is null", newAccessToken);
        assertFalse("Access token is empty", newAccessToken.isEmpty());

        assertNotNull("Refresh token is null", newRefreshToken);
        assertFalse("Refresh token is empty", newRefreshToken.isEmpty());
    }

    @Test
    public void loginWithWrongPasswordTest() {
        User wrongPasswordUser = new User(user.getEmail(), "wrongPassword", null);

        userSteps.loginUser(wrongPasswordUser)
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    public void loginMissingFieldTest() {
        User noPasswordUser = new User(user.getEmail(), null, null);

        userSteps.loginUser(noPasswordUser)
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }


}
