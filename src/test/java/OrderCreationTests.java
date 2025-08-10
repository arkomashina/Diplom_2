import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojoClasses.user.User;
import constants.Headers;
import constants.HttpsMethods;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderCreationTests {

    private String createdUserAccessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = HttpsMethods.URI;
    }

    @After
    public void tearDown() {
        if (createdUserAccessToken != null) {
            given()
                    .header("Authorization", createdUserAccessToken)
                    .when()
                    .delete(HttpsMethods.DELETE_USER);
        }
    }

    private String createUniqueUserTest() {
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
        return createdUserAccessToken;
    }

    @Test
    public void createOrderWithAuthAndIngredientsTest() {
        String token = createUniqueUserTest();
        List<String> ingredients = Arrays.asList("61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa6d");

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

    @Test
    public void createOrderWithoutAuthButWithIngredientsTest() {
        List<String> ingredients = Arrays.asList("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f");

        given()
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(Collections.singletonMap("ingredients", ingredients))
                .when()
                .post(HttpsMethods.POST_CREATE_ORDER)
                .then()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }

    @Test
    public void createOrderWithoutIngredientsTest() {
        String token = createUniqueUserTest();

        given()
                .header("Authorization", token)
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(Collections.singletonMap("ingredients", Collections.emptyList()))
                .when()
                .post(HttpsMethods.POST_CREATE_ORDER)
                .then()
                .statusCode(400)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    public void createOrderWithInvalidIngredientHashTest() {
        String token = createUniqueUserTest();
        List<String> invalidIngredients = Collections.singletonList("invalid_hash_123");

        given()
                .header("Authorization", token)
                .header(Headers.CONTENT_TYPE, Headers.APPLICATION_JSON)
                .body(Collections.singletonMap("ingredients", invalidIngredients))
                .when()
                .post(HttpsMethods.POST_CREATE_ORDER)
                .then()
                .statusCode(500);
    }
}
