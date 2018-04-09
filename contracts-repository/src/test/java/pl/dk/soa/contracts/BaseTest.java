package pl.dk.soa.contracts;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public abstract class BaseTest {

    @Before
    public void init() {
        RestAssured.baseURI = "http://localhost:8081";
    }

}