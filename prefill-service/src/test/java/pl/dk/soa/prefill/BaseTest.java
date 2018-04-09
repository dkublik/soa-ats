package pl.dk.soa.prefill;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = PrefillApplication.class)
@RunWith(SpringRunner.class)
public abstract class BaseTest {

    @Before
    public void init() {
        RestAssured.baseURI = "http://localhost:8081";
    }

}