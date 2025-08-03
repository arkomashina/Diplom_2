import constants.Headers;
import constants.HttpsMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojoClasses.user.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;


public class UserAuthorizationTests {

    private User user;
    private String accessToken;
    private String refreshToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = HttpsMethods.URI;
        user = new User(
                "test-" + System.currentTimeMillis() + "@yandex.ru",
                "password",
                "AuthUser"
        );

        Response registerResponse = given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(user)
                .when()
                .post(HttpsMethods.POST_CREATE_NEW_USER);

        registerResponse.then().statusCode(200).body("success", equalTo(true));
        accessToken = registerResponse.path("accessToken");
        refreshToken = registerResponse.path("refreshToken");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            given()
                    .header(Headers.AUTHORIZATION, accessToken)
                    .when()
                    .delete(HttpsMethods.DELETE_USER)
                    .then()
                    .statusCode(202);
        }
    }

    @Test
    public void successfulLoginTest() {
        Response loginResponse = given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(new User(user.getEmail(), user.getPassword(), null))
                .when()
                .post(HttpsMethods.POST_LOGIN_NEW_USER);

        loginResponse.then().statusCode(200)
                .and()
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

        given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(wrongPasswordUser)
                .when()
                .post(HttpsMethods.POST_LOGIN_NEW_USER)
                .then()
                .statusCode(401)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    public void loginMissingFieldTest() {
        User noPasswordUser = new User(user.getEmail(), null, null);

        given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(noPasswordUser)
                .when()
                .post(HttpsMethods.POST_LOGIN_NEW_USER)
                .then()
                .statusCode(401)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }


}
