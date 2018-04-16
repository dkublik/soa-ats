package pl.dk.soa.apply.example.contract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dk.soa.apply.resource.AppIdResponse;
import pl.dk.soa.apply.resource.Application;
import pl.dk.soa.apply.resource.ApplyController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * requires:
 * undertest: ats-apply-service <- local
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = "pl.dk:prefill-service:+:stubs:8081", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ApplyContractTest {

    @Autowired
    ApplyController applyController;

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

    /** zadanie:
        wzorujac sie na powyzszym, przepisz
        service-tests/pl.dk.soa.apply.ApplyTest,
        aby nie potrzebowal juz rule wiromockowego, a korzystal ze spring cloud contract

        uwaga, poniewaz wiremock spring cloudowy gryzie sie z wiremockiem,
        ktorego wczesniej uzylismy jak walidator kontraktu - odswiez zaleznosci gradlowe!
     */

}