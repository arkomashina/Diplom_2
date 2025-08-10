package tests;

import constants.HttpsMethods;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.OrderSteps;
import steps.UserSteps;

import java.util.Arrays;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderOfUserTests {

    private final UserSteps userSteps = new UserSteps();
    private final OrderSteps orderSteps = new OrderSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = HttpsMethods.URI;
    }

    @After
    public void tearDown() {
        userSteps.deleteUser();
    }


    @Test
    public void getOrdersForAuthorizedUserTest() {
        String token = userSteps.createUserAndGetToken();
        orderSteps.createOrder(token, Arrays.asList("61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa6d"), 200);

        orderSteps.getUserOrders(token)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("orders", notNullValue())
                .body("total", notNullValue())
                .body("totalToday", notNullValue());
    }

    @Test
    public void getOrdersForUnauthorizedUserTest() {
        orderSteps.getUserOrdersWithoutAuth()
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
