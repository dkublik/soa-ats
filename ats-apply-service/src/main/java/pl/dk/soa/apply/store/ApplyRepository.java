package pl.dk.soa.apply.store;

import org.springframework.stereotype.Service;
import pl.dk.soa.apply.notification.MqNotifier;
import pl.dk.soa.apply.verification.ApplicationVerifier;

import java.util.*;

import static java.util.Comparator.comparing;

@Service
public class ApplyRepository {

    private final MqNotifier mqNotifier;

    private final ApplicationVerifier applicationVerifier;

    private Map<String, StoredApplication> applications = new HashMap<>();

    public ApplyRepository(MqNotifier mqNotifier, ApplicationVerifier applicationVerifier) {
        this.mqNotifier = mqNotifier;
        this.applicationVerifier = applicationVerifier;
    }


    public void store(StoredApplication application) {
        applications.put(application.getId(), application);
        mqNotifier.sendNotificationAboutStoredApp(application);
        // TODO uncomment for verification test
     //   applicationVerifier.verifyByExternalService(application);
    }

    public List<StoredApplication> findAll() {
        List<StoredApplication> allApplications = new ArrayList<>(applications.values());
        allApplications.sort(comparing(StoredApplication::getCreatedTime));
        return allApplications;
    }

}
