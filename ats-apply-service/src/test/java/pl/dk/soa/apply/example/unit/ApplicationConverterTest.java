package pl.dk.soa.apply.example.unit;

import org.junit.Test;
import pl.dk.soa.apply.resource.Application;
import pl.dk.soa.apply.resource.ApplicationConverter;
import pl.dk.soa.apply.store.StoredApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationConverterTest {

    ApplicationConverter applicationConverter = new ApplicationConverter();

    @Test
    public void shouldConvertApplication() {
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