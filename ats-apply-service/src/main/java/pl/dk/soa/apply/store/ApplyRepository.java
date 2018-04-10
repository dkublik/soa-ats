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
    private MqNotifier mqNotifier;

    @Autowired
    private ApplicationVerifier applicationVerifier;

    private Map<String, StoredApplication> applications = new HashMap<>();

    public void store(StoredApplication application) {
        applications.put(application.getId(), application);
        if (mqNotifier != null) {
            mqNotifier.sendNotificationAboutStoredApp(application);
        }
        if (applicationVerifier != null) {
            applicationVerifier.verifyByExternalService(application);
        }
    }

    public List<StoredApplication> findAll() {
        List<StoredApplication> allApplications = new ArrayList<>(applications.values());
        allApplications.sort(comparing(StoredApplication::getCreatedTime));
        return allApplications;
    }

}
