package steps;

import constants.Headers;
import constants.HttpsMethods;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderSteps {
    public void createOrder(String token, List<String> ingredients, int expectedStatus) {
        given()
                .header("Authorization", token)
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(Collections.singletonMap("ingredients", ingredients))
                .when()
                .post(HttpsMethods.POST_CREATE_ORDER)
                .then()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }

    public void createOrderWithoutAuth(List<String> ingredients, int expectedStatus) {
        given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(Collections.singletonMap("ingredients", ingredients))
                .when()
                .post(HttpsMethods.POST_CREATE_ORDER)
                .then()
                .statusCode(expectedStatus);
    }

    public void createOrderWithAuth(String token, List<String> ingredients, int expectedStatus) {
        given()
                .header("Authorization", token)
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(Collections.singletonMap("ingredients", ingredients))
                .when()
                .post(HttpsMethods.POST_CREATE_ORDER)
                .then()
                .statusCode(expectedStatus);
    }

    public Response getUserOrders(String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get(HttpsMethods.GET_USER_ORDER)
                .then()
                .extract()
                .response();
    }

    public Response getUserOrdersWithoutAuth() {
        return given()
                .when()
                .get(HttpsMethods.GET_USER_ORDER)
                .then()
                .extract()
                .response();
    }
}
