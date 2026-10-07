package org.example.wholecart.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiBase {

    protected String baseUrl;
    protected String accessToken;

    protected Response get(String endpoint) {

        return given()
                .baseUri(baseUrl)
                .auth()
                .oauth2(accessToken)
                .when()
                .get(endpoint);
    }

    protected Response post(String endpoint, Object requestBody) {

        return given()
                .baseUri(baseUrl)
                .auth()
                .oauth2(accessToken)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    protected Response put(String endpoint, Object requestBody) {

        return given()
                .baseUri(baseUrl)
                .auth()
                .oauth2(accessToken)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(endpoint);
    }
}