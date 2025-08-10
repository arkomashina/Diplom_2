package tests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import constants.HttpsMethods;
import steps.OrderSteps;
import steps.UserSteps;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class OrderCreationTests {


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
    public void createOrderWithAuthAndIngredientsTest() {
        String token = userSteps.createUserAndGetToken();
        List<String> ingredients = Arrays.asList("61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa6d");

        orderSteps.createOrder(token, ingredients, 200);
    }

    @Test
    public void createOrderWithoutAuthButWithIngredientsTest() {
        List<String> ingredients = Arrays.asList("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f");

        orderSteps.createOrderWithoutAuth(ingredients, 200);
    }

    @Test
    public void createOrderWithoutIngredientsTest() {
        String token = userSteps.createUserAndGetToken();

        orderSteps.createOrderWithAuth(token, Collections.emptyList(), 400);
    }

    @Test
    public void createOrderWithInvalidIngredientHashTest() {
        String token = userSteps.createUserAndGetToken();
        List<String> invalidIngredients = Collections.singletonList("invalid_hash_123");

        orderSteps.createOrderWithAuth(token, invalidIngredients, 500);
    }
}
