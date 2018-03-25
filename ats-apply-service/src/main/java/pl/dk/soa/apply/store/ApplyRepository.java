package pl.dk.soa.apply.store;

import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Comparator.comparing;

@Service
public class ApplyRepository {

    private Map<String, StoredApplication> applications = new HashMap<>();

    public void store(StoredApplication application) {
        applications.put(application.getId(), application);
    }

    public List<StoredApplication> findAll() {
        List<StoredApplication> allApplications = new ArrayList<>(applications.values());
        allApplications.sort(comparing(StoredApplication::getCreatedTime));
        return allApplications;
    }

}
