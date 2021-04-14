import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredDeserializationTest {

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
    public void postBillPayment_checkStatusCode_shouldBeHttp415() {

        Address address =
                new Address("My street", "My city", "My state", "90210");

        BillPayment billPayment =
                new BillPayment("Bas", address, "0123456789", 12345);

        given().log().body().
            spec(requestSpec).
            queryParam("accountId", 12345).
            queryParam("amount", 50).
            body(billPayment).
        when().
            post("/billpay").
        then().
            assertThat().
            statusCode(415);
    }

    @Test
    public void getAccount12345_checkType_shouldBeChecking() {

        Account account =

        given().
            spec(requestSpec).
        when().
            get("/accounts/12345").
            as(Account.class);

        Assert.assertEquals("CHECKING", account.getType());
    }
}
