package pl.dk.soa.apply.store;

import org.springframework.stereotype.Service;
import pl.dk.soa.apply.notification.MqNotifier;
import pl.dk.soa.apply.verification.ApplicationVerifier;

import java.util.*;

import static java.util.Comparator.comparing;

@Service
public class ApplyRepository {

    private final Optional<MqNotifier> mqNotifier;

    private final Optional<ApplicationVerifier> applicationVerifier;

    private Map<String, StoredApplication> applications = new HashMap<>();

    public ApplyRepository(Optional<MqNotifier> mqNotifier, Optional<ApplicationVerifier> applicationVerifier) {
        this.mqNotifier = mqNotifier;
        this.applicationVerifier = applicationVerifier;
    }


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
