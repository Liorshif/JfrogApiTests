import com.google.common.base.Objects;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
        User[] allObjectResponse = given()
                .when()
                .get(ALL_OBJECTS_END_POINT)
                .then()
                .extract()
                .as(User[].class);
        List<User> users = Arrays.asList(allObjectResponse);
        User singleObjectResponse = given()
                .when()
                .get(ALL_OBJECTS_END_POINT + "1")
                .then()
                .extract()
                .as(User.class);

        Assert.assertTrue(users.contains(singleObjectResponse));
    }

    static class User {
        int userId;
        int id;
        String title;
        String body;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return userId == user.userId && id == user.id &&
                    Objects.equal(title, user.title) && Objects.equal(body, user.body);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(userId, id, title, body);
        }

        public User() {
        }

        public User(int userId, int id, String title, String body) {
            this.userId = userId;
            this.id = id;
            this.title = title;
            this.body = body;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    @Test
    void testNotExistingObjectNotFoundInAllObjectsEndpoint() {
        JSONObject notExistingObject = new JSONObject();
        notExistingObject.put("name", "sonoo");
        notExistingObject.put("age", Integer.valueOf(27));
        notExistingObject.put("salary", "600000");

        String strNotExsistingObject = notExistingObject.toString();

        String allObjectResponse = given()
                .when()
                .get(ALL_OBJECTS_END_POINT)
                .getBody().asString();
        Assert.assertFalse(allObjectResponse.contains(strNotExsistingObject));
    }
}

