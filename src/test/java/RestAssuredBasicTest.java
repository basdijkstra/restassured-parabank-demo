import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredBasicTest {

    private RequestSpecification requestSpec;

    @Before
    public void createRequestSpec() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8080)
                .setBasePath("/parabank/services/bank/")
                .setAccept(ContentType.JSON)
                .build();
    }

    @Test
    public void getAccount12345_checkStatusCode_shouldBeHttp200() {

        given().
            spec(requestSpec).
        when().
            get("/accounts/12345").
        then().
            assertThat().
            statusCode(200);
    }

    @Test
    public void getAccount12345_checkCustomerId_shouldBe12212() {

        given().
            spec(requestSpec).
        when().
            get("/accounts/12345").
        then().
            assertThat().
            body("customerId", equalTo(12212));
    }
}
