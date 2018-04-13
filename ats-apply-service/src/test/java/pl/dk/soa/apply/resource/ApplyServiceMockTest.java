package pl.dk.soa.apply.resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.dk.soa.apply.store.ApplyRepository;
import pl.dk.soa.apply.store.StoredApplication;

import static org.junit.Assert.assertEquals;

public class ApplyServiceMockTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ApplyRepository applyRepository;

    ApplyService applyService;

    @Before
    public void init() {
        applyService = new ApplyService(applyRepository, new ApplicationConverter());
    }

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
        ArgumentCaptor<StoredApplication> argumentCaptor = ArgumentCaptor.forClass(StoredApplication.class);
        Mockito.verify(applyRepository).store(argumentCaptor.capture());
        StoredApplication capturedValue = argumentCaptor.getValue();
        assertEquals("jSnow", capturedValue.getCandidateId());
        assertEquals("123", capturedValue.getListingId());
        assertEquals("eager to work for the whole winter!", capturedValue.getMessageToRecruiter());
    }

}