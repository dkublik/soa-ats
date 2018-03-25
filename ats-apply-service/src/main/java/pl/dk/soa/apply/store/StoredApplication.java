package pl.dk.soa.apply.store;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

@Getter
@Setter
public class StoredApplication {

    private String id = randomUUID().toString();
    private String candidateId;
    private String messageToRecruiter;
    private String listingId;
    private Instant createdTime = now();

}
