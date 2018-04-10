package pl.dk.soa.apply.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dk.soa.apply.notification.MqNotifier;
import pl.dk.soa.apply.verification.ApplicationVerifier;

import java.util.*;

import static java.util.Comparator.comparing;

@Service
public class ApplyRepository {

    @Autowired
    private Optional<MqNotifier> mqNotifier;

    @Autowired
    private Optional<ApplicationVerifier> applicationVerifier;

    private Map<String, StoredApplication> applications = new HashMap<>();

    public void store(StoredApplication application) {
        applications.put(application.getId(), application);
        mqNotifier.ifPresent(m -> m.sendNotificationAboutStoredApp(application));
        applicationVerifier.ifPresent(v -> v.verifyByExternalService(application));
    }

    public List<StoredApplication> findAll() {
        List<StoredApplication> allApplications = new ArrayList<>(applications.values());
        allApplications.sort(comparing(StoredApplication::getCreatedTime));
        return allApplications;
    }

}
