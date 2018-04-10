package pl.dk.soa.apply.resource;

import org.junit.jupiter.api.Test;
import pl.dk.soa.apply.store.ApplyRepository;
import pl.dk.soa.apply.store.StoredApplication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplyServiceCollaborativeTest {

    ApplyRepository applyRepository = new ApplyRepository();

    ApplicationConverter applicationConverter = new ApplicationConverter();

    ApplyService applyService = new ApplyService(applyRepository, applicationConverter);

    @Test
    void shouldStoreApplication() {
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