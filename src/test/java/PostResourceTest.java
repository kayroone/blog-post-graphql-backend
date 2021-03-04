import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class PostResourceTest {

    @Test
    public void createPost() {

        String requestBody =
                "{\"query\":" +
                        "\"" +
                            "mutation createPost { " +
                                "createPost(post: {" +
                                    "title: \\\"Title\\\" " +
                                    "content: \\\"Content\\\" " +
                                    "author: {" +
                                        "id: \\\"1\\\" " +
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
                                "createPost(post: {" +
                                    "title: \\\"Title\\\" " +
                                    "content: \\\"Content\\\" " +
                                    "author: {" +
                                        "id: \\\"102\\\" " +
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
                .body("errors.message[0]", is("User with id 102 not found."))
                .body("errors.extensions[0].code", is("user-not-found"))
                .statusCode(200);
    }

    @Test
    public void getPost() {

        String requestBody =
                "{\"query\":" +
                        "\"" +
                            "query getPost { " +
                                "post(id: \\\"1\\\") " +
                                    "{ " +
                                        "title " +
                                        "content " +
                                        "author { " +
                                            "id " +
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
                .body("data.post.size()", is(3))
                .body("data.post.title", is("Test"))
                .body("data.post.content", is("Test"))
                .body("data.post.author.id", is(1))
                .statusCode(200);
    }

    @Test
    public void getPostWithNonExistingPostShouldFail() {

        String requestBody =
                "{\"query\":" +
                        "\"" +
                            "query getPost { " +
                                "post(id: \\\"102\\\") " +
                                    "{ " +
                                        "title " +
                                        "content " +
                                        "author { " +
                                            "id " +
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
                .body("errors.message[0]", is("Post with id 102 not found."))
                .body("errors.extensions[0].code", is("post-not-found"))
                .statusCode(200);
    }

    @Test
    public void getPostsByTitleKeywordTwoResults() {

        String requestBody =
                "{\"query\":" +
                        "\"" +
                            "query getPostByKeyword { " +
                                "postsByTitleKeyword(keyword: \\\"Test\\\") " +
                                    "{ " +
                                        "title " +
                                        "content " +
                                        "author { " +
                                            "id " +
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
                .body("data.postsByTitleKeyword.size()", is(2))
                .statusCode(200);
    }

    @Test
    public void getPostsByTitleKeywordOneResult() {

        String requestBody =
                "{\"query\":" +
                        "\"" +
                            "query getPostByKeyword { " +
                                "postsByTitleKeyword(keyword: \\\"Foo\\\") " +
                                    "{ " +
                                        "title " +
                                        "content " +
                                        "author { " +
                                            "id " +
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
                .body("data.postsByTitleKeyword.size()", is(1))
                .statusCode(200);
    }
}
