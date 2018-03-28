package pl.dk.soa.apply.store;

import lombok.Getter;
import lombok.Setter;
import pl.dk.soa.apply.domainevent.DomainEvents;

import java.time.Instant;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;
import static pl.dk.soa.apply.store.StoredApplication.Status.ACCEPTED;
import static pl.dk.soa.apply.store.StoredApplication.Status.NEW;
import static pl.dk.soa.apply.store.StoredApplication.Status.REJECTED;

@Getter
@Setter
public class StoredApplication {

    public enum Status {
        NEW,
        ACCEPTED,
        REJECTED
    }

    private String id = randomUUID().toString();
    private String candidateId;
    private String messageToRecruiter;
    private String listingId;
    private Instant createdTime = now();
    private Status status = NEW;

    public void stored() {
        DomainEvents.publish(new ApplicationStoredEvent(this));
    }

    public void accepted() {
        status = ACCEPTED;
    }

    public void rejected() {
        status = REJECTED;
    }

}
