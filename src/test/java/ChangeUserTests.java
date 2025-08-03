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
import static org.junit.Assert.assertNotNull;

public class ChangeUserTests {

    private User user;
    private String accessToken;

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

        accessToken = registerResponse.path("accessToken");
        assertNotNull("Поле токена не может быть пустым", accessToken);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            RestAssured.given()
                    .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                    .header("authorization", accessToken)
                    .delete(HttpsMethods.DELETE_USER)
                    .then().statusCode(202);
        }
    }

    @Test
    public void updateUserDataWithAuthTest() {
        String newName = "UpdatedName";
        String newEmail = "updated" + System.currentTimeMillis() + "@mail.ru";

        User updatedUser = new User(newEmail, null,  newName);

        RestAssured.given().log().all()
                .header(Headers.AUTHORIZATION, accessToken)
                .contentType(Headers.APPLICATION_JSON)
                .body(updatedUser)
                .patch(HttpsMethods.PATCH_UPDATE_USER)
                .then().log().all()
                .statusCode(200).log().all()
                .body("success", equalTo(true))
                .body("user.email", equalTo(newEmail))
                .body("user.name", equalTo(newName));
    }

    @Test
    public void updateUserDataWithoutAuthTest() {
        String newName = "UpdatedNameWithoutAuth";
        String newEmail = "updatedNoAuth" + System.currentTimeMillis() + "@mail.ru";

        User updatedUser = new User(newEmail, null, newName);

        RestAssured.given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(updatedUser)
                .patch(HttpsMethods.PATCH_UPDATE_USER)
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
