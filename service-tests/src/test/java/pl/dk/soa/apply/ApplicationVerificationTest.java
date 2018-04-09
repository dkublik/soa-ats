package pl.dk.soa.apply;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

class ApplicationVerificationTest {

    @Test
    public void shouldStoreApplicationWithRejectedStatus() throws Exception {
        // given:
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new JSONObject()
                        .put("candidateId", "just_britney")
                        .put("messageToRecruiter", "BAD_APPLICATION")
                        .put("listingId", "1")
                        .toString()
                );
        // when:
        Response response = request.when()
                .post("/v1/job-applications");

        // then:
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("status", Matchers.is("REJECTED"));
    }

    @Test
    public void shouldStoreApplicationWithApproved() throws Exception {
        // given:
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new JSONObject()
                        .put("candidateId", "just_britney")
                        .put("messageToRecruiter", "any message")
                        .put("listingId", "1")
                        .toString()
                );
        // when:
        Response response = request.when()
                .post("/v1/job-applications");

        // then:
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("status", Matchers.is("ACCEPTED"));
    }

}