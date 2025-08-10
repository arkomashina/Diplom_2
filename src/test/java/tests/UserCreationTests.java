package tests;

import pojoClasses.user.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import constants.HttpsMethods;
import steps.UserSteps;


import static org.hamcrest.CoreMatchers.equalTo;


public class UserCreationTests {

    private String createdUserAccessToken;
    private final UserSteps userSteps = new UserSteps();

    @After
    public void tearDown() {
        if (createdUserAccessToken != null) {
            userSteps.deleteUser();
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
        Response response = userSteps.registerUser(user);

        response.then().statusCode(200).body("success", equalTo(true));

        createdUserAccessToken = response.path("accessToken");
    }

    @Test
    public void createUserAlreadyRegisteredTest() {
        String existingEmail = "test-" + System.currentTimeMillis() + "@yandex.ru";
        User user = new User(existingEmail, "password", "ExistingUser");


        Response firstResponse = userSteps.registerUser(user);
        firstResponse.then().statusCode(200);
        createdUserAccessToken = firstResponse.path("accessToken");

        userSteps.registerUser(user)
                .then()
                .statusCode(403)
                .body("message", equalTo("User already exists"));
    }

    @Test
    public void createUserMissingRequiredFieldTest() {

        User incompleteUser = new User(
                "test-" + System.currentTimeMillis() + "@yandex.ru",
                "password",
                null
        );

        userSteps.registerUser(incompleteUser)
                .then()
                .statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
