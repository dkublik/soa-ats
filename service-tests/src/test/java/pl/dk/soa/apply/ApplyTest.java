package pl.dk.soa.apply;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import pl.dk.soa.Hosts;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.notNullValue;

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
        Response response = request.when().get(Hosts.APPLY_HOST + "/v1/job-applications");

        // then
        response.then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("applicationId", notNullValue());
    }

}