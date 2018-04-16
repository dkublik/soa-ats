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
 *  ats-apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication must be up & running
 *  http://localhost:8080
 */
public class ApplyTest {

    @Test
    public void shouldApplySuccessfully() throws Exception {
        
    }

}