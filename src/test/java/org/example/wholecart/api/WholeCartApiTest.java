package org.example.wholecart.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class WholeCartApiTest extends ApiBase {

    private String username;
    private String password;

    private int productId;
    private String unit;
    private int quantity;

    @BeforeClass
    public void setupApi() {

        loadTestData();

        Assert.assertNotNull(baseUrl, "Base URL should not be null.");

        System.out.println("==============================================");
        System.out.println("WholeCart API Test Setup");
        System.out.println("Base URL: " + baseUrl);
        System.out.println("Username: " + username);
        System.out.println("Product ID: " + productId);
        System.out.println("Cart Unit: " + unit);
        System.out.println("Cart Quantity: " + quantity);
        System.out.println("==============================================");
    }

    @Test(priority = 1)
    public void loginApiTest() {

        Map<String, String> loginRequest = new HashMap<>();

        loginRequest.put("username", username);
        loginRequest.put("password", password);

        Response response =
                given()
                        .baseUri(baseUrl)
                        .contentType("application/json")
                        .body(loginRequest)
                        .when()
                        .post("/api/login");

        System.out.println("==============================================");
        System.out.println("LOGIN API");
        System.out.println("==============================================");
        System.out.println("Login API Status: " + response.statusCode());
        System.out.println("Login API Response:");
        System.out.println(response.asPrettyString());

        response.then().statusCode(200);

        accessToken = response.jsonPath().getString("token");

        Assert.assertNotNull(accessToken, "Login succeeded, but token was not found in the response.");

        Assert.assertFalse(accessToken.isBlank(), "Login token should not be empty.");

        System.out.println("Login successful.");
        System.out.println("Authentication token generated successfully.");
        System.out.println("User Role: " + response.jsonPath().getString("role"));
        System.out.println("User Name: " + response.jsonPath().getString("name"));
        System.out.println("==============================================");
    }

    @Test(priority = 2, dependsOnMethods = "loginApiTest")
    public void getProductApiTest() {
        Response response = get("/api/products/" + productId);

        System.out.println("==============================================");
        System.out.println("GET PRODUCT API");
        System.out.println("==============================================");
        System.out.println("Product API Status: " + response.statusCode());
        System.out.println("Product API Response:");
        System.out.println(response.asPrettyString());

        response.then().statusCode(200);

        int responseProductId = response.jsonPath().getInt("id");

        Assert.assertEquals(responseProductId, productId, "Product ID returned by API does not match the requested product ID.");
        String productName = response.jsonPath().getString("name");
        Assert.assertNotNull(productName, "Product name should be available.");
        Assert.assertFalse(productName.isBlank(), "Product name should not be empty.");

        System.out.println("Product ID: " + responseProductId);
        System.out.println("Product Name: " + productName);
        System.out.println("Product API validation successful.");
    }

    @Test(priority = 3, dependsOnMethods = "loginApiTest")
    public void setCartItemApiTest() {

        Map<String, Object> cartRequest = new HashMap<>();

        cartRequest.put("product_id", productId);
        cartRequest.put("unit", unit);
        cartRequest.put("qty", quantity);

        System.out.println("==============================================");
        System.out.println("ADD PRODUCT TO CART API");
        System.out.println("==============================================");

        Response addResponse = post("/api/cart/items", cartRequest);

        System.out.println("Add Cart API Status: " + addResponse.statusCode());
        System.out.println("Add Cart API Response:");

        if (addResponse.getBody() != null) {
            System.out.println(addResponse.asPrettyString());
        }

        Assert.assertTrue(
                addResponse.statusCode() == 200 ||
                        addResponse.statusCode() == 201,
                "Product could not be added to cart. Actual status: "
                        + addResponse.statusCode()
        );

        System.out.println("Product added to cart successfully.");
        System.out.println("Product ID: " + productId);
        System.out.println("Unit: " + unit);
        System.out.println("Quantity: " + quantity);
        System.out.println("==============================================");
    }

    private void loadTestData() {
        try {
            InputStream inputStream =
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream(
                                    "testdata/api_test_data.json"
                            );

            Assert.assertNotNull(inputStream, "API test data file was not found: testdata/api_test_data.json");

            JsonPath jsonPath = new JsonPath(inputStream);

            baseUrl = jsonPath.getString("baseUrl");
            username = jsonPath.getString("login.username");
            password = jsonPath.getString("login.password");
            productId = jsonPath.getInt("product.productId");
            unit = jsonPath.getString("cart.unit");
            quantity = jsonPath.getInt("cart.quantity");

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to load API test data.",
                    e
            );
        }
    }
}
