package steps;

import constants.Headers;
import constants.HttpsMethods;
import io.restassured.response.Response;
import pojoClasses.user.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserSteps {
    private String createdUserAccessToken;

    public String getCreatedUserAccessToken() {
        return createdUserAccessToken;
    }

    public String createUserAndGetToken(User user) {
        Response response = given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(user)
                .when()
                .post(HttpsMethods.POST_CREATE_NEW_USER);

        response.then().statusCode(200).and().body("success", equalTo(true));
        createdUserAccessToken = response.then().extract().path("accessToken");
        return createdUserAccessToken;
    }

    public String createUserAndGetToken() {
        User user = new User(
                "test-" + System.currentTimeMillis() + "@yandex.ru",
                "password",
                "TestUser"
        );
        return createUserAndGetToken(user); // вызов существующего метода
    }

    public void deleteUser() {
        if (createdUserAccessToken != null) {
            given()
                    .header("Authorization", createdUserAccessToken)
                    .when()
                    .delete(HttpsMethods.DELETE_USER)
                    .then()
                    .statusCode(202);
        }
    }

    public Response updateUserData(String token, User updatedUser) {
        return given()
                .header(Headers.AUTHORIZATION, token)
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(updatedUser)
                .when()
                .patch(HttpsMethods.PATCH_UPDATE_USER);
    }

    public Response updateUserDataWithoutAuth(User updatedUser) {
        return given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(updatedUser)
                .when()
                .patch(HttpsMethods.PATCH_UPDATE_USER);
    }

    public Response registerUser(User user) {
        return given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(user)
                .when()
                .post(HttpsMethods.POST_CREATE_NEW_USER)
                .then()
                .extract()
                .response();
    }

    public Response loginUser(User user) {
        return given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(user)
                .when()
                .post(HttpsMethods.POST_LOGIN_NEW_USER)
                .then()
                .extract()
                .response();
    }
}
