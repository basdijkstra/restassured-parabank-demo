import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class RestAssuredDataDrivenTest {

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
    @DataProvider({
            "12345, 12212, CHECKING",
            "12678, 12212, SAVINGS",
            "13455, 12323, CHECKING"
    })
    public void getAccount_checkCustomerIdAndType_shouldMatchExpected(int accountId, int customerId, String accountType) {

        given().
            pathParam("accountId", accountId).
            spec(requestSpec).
        when().
            get("/accounts/{accountId}").
        then().
            assertThat().
            body("customerId", equalTo(customerId)).
        and().
            body("type", equalTo(accountType));
    }
}
