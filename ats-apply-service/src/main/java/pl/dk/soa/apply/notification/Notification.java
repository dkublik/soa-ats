package pl.dk.soa.apply.notification;

import lombok.Getter;
import pl.dk.soa.apply.store.StoredApplication;

import java.time.Instant;

@Getter
public class Notification {

    private String applicationId;
    private String candidateId;
    private String firstName;
    private String lastName;
    private String email;
    private String messageToRecruiter;
    private String listingId;
    private Instant applicationDate;

    Notification(StoredApplication application, Prefill additionalData) {
        applicationId = application.getId();
        applicationDate = application.getCreatedTime();
        candidateId = application.getCandidateId();
        listingId = application.getListingId();
        messageToRecruiter = application.getMessageToRecruiter();
        firstName = additionalData.getFirstName();
        lastName = additionalData.getLastName();
        email = additionalData.getEmail();
    }

}
