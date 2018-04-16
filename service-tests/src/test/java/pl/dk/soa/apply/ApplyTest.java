package pl.dk.soa.apply;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 *  must be up & running
 *  ats-apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication http://localhost:8080
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = "pl.dk:prefill-service:+:stubs:8081", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ApplyTest {

    @Test
    public void shouldApplySuccessfully() throws Exception {
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
        Response response = request.when().post("http://localhost:8080/v1/job-applications");

        // then
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("applicationId", notNullValue())
                .body("priority", CoreMatchers.is("HIGH"));
    }

}