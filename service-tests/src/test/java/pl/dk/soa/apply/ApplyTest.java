package pl.dk.soa.apply;

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

    /* zadanie:
        w pliku /src/test/resources/mappings umiesc scenariusz wiremock -> getPrefill.json,
        ktory dla uzytkownika z testu powyzej (just_britney)
        zwroci conajmniej:
        - adress email,
        - wiecej niz 10 lat doswiadczenia

        skladnie wiremock mozesz podejrzec w plikach, ktore byly przykladami w cwiczeniu z samochodem, np.
        https://github.com/dkublik/soa-car-template/blob/master/mocks/src/main/resources/mappings/_gps-coordinates.json

        dokladne nazwy parametrow w odpowiedzi i adres mozesz znalezc w swagerze dla prefill-service
        http://localhost:8081/swagger-ui.html
        (do wejscia na url /swagger-ui.htm -> prefill-serwis musi byc odpalony,
        do poprawnego dzialania testu - zastopowany)
        */



}