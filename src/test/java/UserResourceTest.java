import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class UserResourceTest {

    @Test
    public void createUser() {

        String requestBody =
                "{\"query\":" +
                        "\"" +
                            "mutation createUser { " +
                                "createUser ( " +
                                    "name: \\\"Test\\\" " +
                                "){ " +
                                    "id " +
                                    "name " +
                                "}" +
                            "}" +
                        "\"" +
                        "}";

        given()
                .body(requestBody)
                .post("/graphql/")
                .then()
                .contentType(ContentType.JSON)
                .body("data.createUser.size()", is(2))
                .body("data.createUser.id", is(1))
                .body("data.createUser.name", is("Test"))
                .statusCode(200);
    }

}
