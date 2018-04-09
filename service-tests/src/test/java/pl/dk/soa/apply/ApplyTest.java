package pl.dk.soa.apply;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.dk.soa.Hosts;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.notNullValue;

@SpringBootTest
@AutoConfigureStubRunner(ids = "pl.dk:prefill-service:+:stubs:8183", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@ExtendWith(SpringExtension.class)
class ApplyTest {

    @Test
    void shouldGetDataForPrefill() throws Exception {
        // given
        RequestSpecification request = given()
                .contentType(JSON)
                .body(new JSONObject()
                        .put("candidateId", "just_britney")
                        .put("messageToRecruiter", "please hire me")
                        .put("listingId", "123")
                        .toString()
                );

        // when
        Response response = request.when().post(Hosts.APPLY_HOST + "/v1/job-applications");

        // then
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("applicationId", notNullValue());
    }

}