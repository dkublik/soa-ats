package pl.dk.soa.apply.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dk.soa.apply.store.ApplyRepository;
import pl.dk.soa.apply.store.StoredApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ApplyServiceByMockTest {

    @Mock
    ApplyRepository applyRepository;

    ApplyService applyService;

    @BeforeEach
    void init() {
        applyService = new ApplyService(applyRepository, new ApplicationConverter());
    }

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
        ArgumentCaptor<StoredApplication> argumentCaptor = ArgumentCaptor.forClass(StoredApplication.class);
        Mockito.verify(applyRepository).store(argumentCaptor.capture());
        StoredApplication capturedValue = argumentCaptor.getValue();
        assertEquals("jSnow", capturedValue.getCandidateId());
        assertEquals("123", capturedValue.getListingId());
        assertEquals("eager to work for the whole winter!", capturedValue.getMessageToRecruiter());
    }

}
