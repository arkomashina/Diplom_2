import pojoClasses.User.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojoClasses.constants.Headers;
import pojoClasses.constants.HttpsMethods;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;


public class UserCreationTests {

    private String createdUserAccessToken;

    @After
    public void tearDown() {
        if (createdUserAccessToken != null) {
            given()
                    .header("Authorization", createdUserAccessToken)
                    .when()
                    .delete(HttpsMethods.DELETE_USER);
        }
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = HttpsMethods.URI;
    }

    @Test
    public void createUniqueUserTest() {
        User user = new User(
                "test-" + System.currentTimeMillis() + "@yandex.ru",
                "password",
                "UniqueUser"
        );
        Response response = given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(user)
                .when()
                .post(HttpsMethods.POST_CREATE_NEW_USER);

        response.then().statusCode(200).and().body("success", equalTo(true));

        createdUserAccessToken = response.then().extract().path("accessToken");
    }

    @Test
    public void createUserAlreadyRegisteredTest() {
        String existingEmail = "test-" + System.currentTimeMillis() + "@yandex.ru";
        User user = new User(existingEmail, "password", "ExistingUser");


        Response firstResponse = given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(user)
                .when()
                .post(HttpsMethods.POST_CREATE_NEW_USER);

        firstResponse.then().statusCode(200);
        createdUserAccessToken = firstResponse.then().extract().path("accessToken");


        Response secondResponse = given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(user)
                .when()
                .post(HttpsMethods.POST_CREATE_NEW_USER);

        secondResponse.then()
                .statusCode(403)
                .and()
                .body("message", equalTo("User already exists"));
    }

    @Test
    public void createUserMissingRequiredFieldTest() {

        User incompleteUser = new User(
                "test-" + System.currentTimeMillis() + "@yandex.ru",
                "password",
                null
        );

        given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(incompleteUser)
                .when()
                .post(HttpsMethods.POST_CREATE_NEW_USER)
                .then()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
