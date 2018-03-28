package pl.dk.soa.apply.verification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.dk.soa.apply.store.StoredApplication;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationToVerify {

    private String candidateId;
    private String messageToRecruiter;
    private String listingId;

    public ApplicationToVerify(StoredApplication storedApplication) {
        candidateId = storedApplication.getCandidateId();
        messageToRecruiter = storedApplication.getMessageToRecruiter();
        listingId = storedApplication.getListingId();
    }

}
