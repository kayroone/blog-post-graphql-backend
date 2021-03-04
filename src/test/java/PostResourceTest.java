import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class PostResourceTest {

    @BeforeEach
    public void createUser() {

        String requestBody =
                "{\"query\":" +
                        "\"" +
                            "mutation createUser { " +
                                "createUser (user: {" +
                                    "name: \\\"Test\\\" " +
                            "}) {" +
                                "name" +
                            "}" +
                        "}" +
                        "\"" +
                        "}";

        given()
                .body(requestBody)
                .post("/graphql/")
                .then()
                .contentType(ContentType.JSON)
                .body("data.createUser.size()", is(1))
                .body("data.createUser.name", is("Test"))
                .statusCode(200);
    }

    @Test
    public void createPost() {

        String requestBody =
                "{\"query\":" +
                        "\"" +
                            "mutation createPost { " +
                                "createPost (post: {" +
                                    "title: \\\"Title\\\" " +
                                    "content: \\\"Content\\\" " +
                                    "author: {" +
                                        "name: \\\"Test\\\" " +
                                    "}" +
                                "}) {" +
                                    "title " +
                                    "content " +
                                    "author {" +
                                        "name " +
                                    "}" +
                                "}" +
                            "}" +
                        "\"" +
               "}";

        given()
                .body(requestBody)
                .post("/graphql/")
                .then()
                .contentType(ContentType.JSON)
                .body("data.createPost.size()", is(3))
                .body("data.createPost.title", is("Title"))
                .body("data.createPost.content", is("Content"))
                .body("data.createPost.author.name", is("Test"))
                .statusCode(200);
    }

    @Test
    public void createPostWithNonExistingUserShouldFail() {

        String requestBody =
                "{\"query\":" +
                        "\"" +
                        "mutation createPost { " +
                        "createPost (post: {" +
                        "title: \\\"Title\\\" " +
                        "content: \\\"Content\\\" " +
                        "author: {" +
                        "name: \\\"Foo\\\" " +
                        "}" +
                        "}) {" +
                        "title " +
                        "content " +
                        "author {" +
                        "name " +
                        "}" +
                        "}" +
                        "}" +
                        "\"" +
                        "}";

        given()
                .body(requestBody)
                .post("/graphql/")
                .then()
                .contentType(ContentType.JSON)
                .body("errors.message[0]", is("User with name Foo doesn't exist."))
                .body("errors.extensions[0]", is("User with name Foo doesn't exist."))
                .statusCode(200);
    }
}
