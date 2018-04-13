package pl.dk.soa.apply.resource;

import org.junit.Test;
import pl.dk.soa.apply.store.ApplyRepository;
import pl.dk.soa.apply.store.StoredApplication;

import static org.junit.Assert.assertEquals;

public class ApplyServiceStubTest {

    ApplyRepositoryStub applyRepository = new ApplyRepositoryStub();

    ApplyService applyService = new ApplyService(applyRepository, new ApplicationConverter());

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
        StoredApplication lastApplication = applyRepository.getLastApplication();
        assertEquals("jSnow", lastApplication.getCandidateId());
        assertEquals("123", lastApplication.getListingId());
        assertEquals("eager to work for the whole winter!", lastApplication.getMessageToRecruiter());
    }

    class ApplyRepositoryStub extends ApplyRepository {

        private StoredApplication lastApplication;

        @Override
        public void store(StoredApplication application) {
            this.lastApplication = application;
        }

        public StoredApplication getLastApplication() {
            return lastApplication;
        }
    }

}