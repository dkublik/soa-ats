package pl.dk.soa.apply;

import com.atlassian.oai.validator.restassured.SwaggerValidationFilter;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 *  must be up & running
 *  ats-apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication http://localhost:8080
 *  ats-prefill-service/src/main/java/pl.dk.soa.prefill.PrefillApplication http://localhost:8081
 */
public class ApplyTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8081);

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


    SwaggerValidationFilter validationFilter = new SwaggerValidationFilter("swagger-prefill.json");

    @Test
    public void prefillStubShouldBeCompatibleWithSwagger()  {
        // given
        RequestSpecification request = given()
                .filter(validationFilter);

        // when
        Response response = request.when().get("http://localhost:8081/v1/prefill/for-candidate/just_britney");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }



}