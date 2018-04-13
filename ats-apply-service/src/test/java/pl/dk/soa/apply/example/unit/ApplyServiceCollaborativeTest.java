package pl.dk.soa.apply.example.unit;

import org.junit.Test;
import pl.dk.soa.apply.resource.Application;
import pl.dk.soa.apply.resource.ApplicationConverter;
import pl.dk.soa.apply.resource.ApplyService;
import pl.dk.soa.apply.store.ApplyRepository;
import pl.dk.soa.apply.store.StoredApplication;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplyServiceCollaborativeTest {

    ApplyRepository applyRepository = new ApplyRepository();

    ApplicationConverter applicationConverter = new ApplicationConverter();

    ApplyService applyService = new ApplyService(applyRepository, applicationConverter);

    @Test
    public void shouldStoreApplication() {
        // given
        Application application = new Application();
        application.setCandidateId("jSnow");
        application.setListingId("123");
        application.setMessageToRecruiter("eager to work for the whole winter!");

        // when
        applyService.apply(application);

        // then
        List<StoredApplication> allApplications = applyRepository.findAll();
        assertEquals(1, allApplications.size());
        StoredApplication storedApplication = allApplications.get(0);
        assertNotNull(storedApplication.getId());
        assertEquals("jSnow", storedApplication.getCandidateId());
        assertEquals("123", storedApplication.getListingId());
        assertEquals("eager to work for the whole winter!", storedApplication.getMessageToRecruiter());
    }

}