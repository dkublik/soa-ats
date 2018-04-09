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
         /* jezeli uderzamy do ats-apply-service ,

         z wiadomoscia ktora w pole messageToRecruiter ma rowne "BAD_APPLICATION"
         niezaleznie od innych pol

         to dostaniemy odpowiedz

         {
            "status": "REJECTED"
         }

         */
    }

    @Test
    public void shouldStoreApplicationWithApproved() throws Exception {
        /* jezeli uderzamy do ats-apply-service ,

         z wiadomoscia ktora w pole messageToRecruiter ma rowne "any message"
         niezaleznie od innych pol

         to dostaniemy odpowiedz

         {
            "status": "ACCEPTED"
         }

         */
    }

}