package pl.dk.soa.apply.resource;

import org.springframework.stereotype.Service;
import pl.dk.soa.apply.store.StoredApplication;

@Service
public class ApplicationConverter {

    public StoredApplication toStoredApplication(Application application) {
        StoredApplication storedApplication = new StoredApplication();
        storedApplication.setCandidateId(application.getCandidateId());
        storedApplication.setListingId(application.getListingId());
        storedApplication.setMessageToRecruiter(application.getMessageToRecruiter());
        return storedApplication;
    }

}
