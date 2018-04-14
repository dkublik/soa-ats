package pl.dk.soa.apply.example.integration;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dk.soa.apply.resource.AppIdResponse;
import pl.dk.soa.apply.resource.Application;
import pl.dk.soa.apply.resource.ApplyController;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * requires:
 * undertest: ats-apply-service <- local
 * dependency: prefill-service: 8081
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ApplyIntegrationTest {

    @Autowired
    ApplyController applyController;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8081));

    @Test
    public void shouldAcceptApplication() {
        // given
        Application application = new Application();
        application.setCandidateId("just_britney");
        application.setListingId("123");
        application.setMessageToRecruiter("eager to work for the whole winter!");

        // when
        AppIdResponse appIdResponse = applyController.applyForJob(application).getBody();

        // then
        assertNotNull(appIdResponse.getApplicationId());
        assertEquals(appIdResponse.getStatus(), "NEW");
    }

}