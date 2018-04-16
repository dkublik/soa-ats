package pl.dk.soa.apply;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
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

    @Test
    public void shouldApplySuccessfully() throws Exception {
        // przepisz pl.dk.soa.apply.example.integration.ApplyIntegrationTest
        // na test rest assured

        // uwaga - test ten bedzie testem zewnetrznym w stosunku do aplikacji
        // wiec w przeciwienstwie do ApplyIntegrationTest ApplyApplication bedzie musiala byc odpalona
        // odpalona bedzie musiala byc tez jej zaleznosc: prefill-service


        // jesli potrzeba skladni testu testu rest assured - mozna ja znalezc w cwiczeniu o samochodach
        // np. https://github.com/dkublik/soa-car-template/blob/master/mocks/src/test/java/contract/BooksContractVerifierTest.java

        // dane niezbedne do wykonania requestu i sprawdzenie response mozna znalezc w swagerze:
        // http://localhost:8080/swagger-ui.html

    }

}