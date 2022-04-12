import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class ApiTests {

    private static final String ALL_OBJECTS_END_POINT = "https://jsonplaceholder.typicode.com/posts/";
    private static final String EXPECTED_BODY = "quia et suscipit\n" +
            "suscipit recusandae consequuntur expedita et cum\n" +
            "reprehenderit molestiae ut ut quas totam\n" +
            "nostrum rerum est autem sunt rem eveniet architecto";
    private static final String TITLE = "sunt aut facere repellat provident " +
            "occaecati excepturi optio reprehenderit";
    private static final int SUCCESS_CODE = 200;
    private static final int FAILURE_CODE = 404;

    @Test
    void testAllObjectEndPointGetRequest() {
        given()
                .when()
                .get(ALL_OBJECTS_END_POINT + "1")
                .then()
                .statusCode(SUCCESS_CODE)
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", equalTo(TITLE))
                .body("body", equalTo(EXPECTED_BODY));
    }

    @Test
    void testAllObjectsEndPointInvalidGetRequest() {
        given()
                .when()
                .get(ALL_OBJECTS_END_POINT + "a")
                .then()
                .statusCode(FAILURE_CODE);
    }

    @Test
    void testAllObjectsEndPointNotExistValueGetRequest() {
        given()
                .when()
                .get(ALL_OBJECTS_END_POINT + "150")
                .then()
                .statusCode(FAILURE_CODE);
    }

    @Test
    void testIsSingleObjectFoundInAllObjectsEndpoint() {
        Posts[] allObjectResponse = given()
                .when()
                .get(ALL_OBJECTS_END_POINT)
                .then()
                .extract()
                .as(Posts[].class);
        List<Posts> posts = Arrays.asList(allObjectResponse);
        Posts singleObjectResponse = given()
                .when()
                .get(ALL_OBJECTS_END_POINT + "1")
                .then()
                .extract()
                .as(Posts.class);

        Assert.assertTrue(posts.contains(singleObjectResponse));
    }

    @Test
    void testInvalidTypeOfIdParamGetRequest() {
        given().
                param("id", "lior").
                when().
                get(ALL_OBJECTS_END_POINT).
                then().
                statusCode(SUCCESS_CODE).
                assertThat().body("", anyOf(nullValue(), empty()));
    }

    @Test
    void testInvalidTypeOfUserIdParamGetRequest() {
        given().
                param("userId", "hello").
                when().
                get(ALL_OBJECTS_END_POINT).
                then().
                statusCode(SUCCESS_CODE).
                assertThat().body("", anyOf(nullValue(), empty()));
    }

    @Test
    void testInvalidTypeOfTitleParamGetRequest() {
        given().
                param("title", 55).
                when().
                get(ALL_OBJECTS_END_POINT).
                then().
                statusCode(SUCCESS_CODE).
                assertThat().body("", anyOf(nullValue(), empty()));
    }

    @Test
    void testInvalidTypeOfBodyParamGetRequest() {
        given().
                param("body", 2323).
                when().
                get(ALL_OBJECTS_END_POINT).
                then().
                statusCode(SUCCESS_CODE).
                assertThat().body("", anyOf(nullValue(), empty()));
    }
}

