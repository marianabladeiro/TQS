package com.example.P1_REST;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredTest {

    //the endpoint to list all To Do is available (status code 200)
    @Test
    public void whenGetUrlThenStatusCode200() {

        String URL = "https://jsonplaceholder.typicode.com/todos";
        given().when().get(URL).then().assertThat().statusCode(200);
    }

    //the title of To Do #4 is “et porro tempora”
    @Test
    public void whenGetTodoThenCheckTitle() {
        String URL = "https://jsonplaceholder.typicode.com/todos/4";

        given()
                .when()
                .get(URL)
                .then().assertThat()
                .statusCode(200)
                .and().body("id", equalTo(4))
                .and().body("title", equalTo("et porro tempora"));
    }

    //When listing all “todos”, you get id #198 and #199 in the results.
    @Test
    public void whenGetTodoCheckElements() {

        String URL = "https://jsonplaceholder.typicode.com/todos";
        given().when().get(URL).then().assertThat().statusCode(200).and().body("id", hasItems(198,199));
    }
}
