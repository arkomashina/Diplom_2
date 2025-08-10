package tests;

import constants.HttpsMethods;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojoClasses.user.User;
import steps.UserSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;

public class ChangeUserTests {

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



        accessToken = userSteps.createUserAndGetToken(user);
        assertNotNull("Поле токена не может быть пустым", accessToken);
    }

    @After
    public void tearDown() {
        userSteps.deleteUser();
    }

    @Test
    public void updateUserDataWithAuthTest() {
        String newName = "UpdatedName";
        String newEmail = "updated" + System.currentTimeMillis() + "@mail.ru";

        User updatedUser = new User(newEmail, null,  newName);

        userSteps.updateUserData(accessToken, updatedUser)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(newEmail))
                .body("user.name", equalTo(newName));
    }

    @Test
    public void updateUserDataWithoutAuthTest() {
        String newName = "UpdatedNameWithoutAuth";
        String newEmail = "updatedNoAuth" + System.currentTimeMillis() + "@mail.ru";

        User updatedUser = new User(newEmail, null, newName);

        userSteps.updateUserDataWithoutAuth(updatedUser)
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
