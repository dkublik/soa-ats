package pl.dk.soa.apply.resource;

import org.junit.jupiter.api.Test;
import pl.dk.soa.apply.store.StoredApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationConverterTest {

    ApplicationConverter applicationConverter = new ApplicationConverter();

    @Test
    void shouldConvertApplication() {
        // given
        Application application = new Application();
        application.setCandidateId("jSnow");
        application.setListingId("123");
        application.setMessageToRecruiter("eager to work for the whole winter!");

        // when
        StoredApplication storedApplication = applicationConverter.toStoredApplication(application);

        // then
        assertNotNull(storedApplication.getId());
        assertEquals("jSnow", storedApplication.getCandidateId());
        assertEquals("123", storedApplication.getListingId());
        assertEquals("eager to work for the whole winter!", storedApplication.getMessageToRecruiter());
    }

}