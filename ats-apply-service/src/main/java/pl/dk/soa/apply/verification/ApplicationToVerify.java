package pl.dk.soa.apply.verification;

import pl.dk.soa.apply.store.StoredApplication;

public class ApplicationToVerify {

    private String candidateId;
    private String messageToRecruiter;
    private String listingId;

    public ApplicationToVerify(StoredApplication storedApplication) {
        candidateId = storedApplication.getCandidateId();
        messageToRecruiter = storedApplication.getMessageToRecruiter();
        listingId = storedApplication.getListingId();
    }

    public ApplicationToVerify() {
    }

    public String getCandidateId() {
        return this.candidateId;
    }

    public String getMessageToRecruiter() {
        return this.messageToRecruiter;
    }

    public String getListingId() {
        return this.listingId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public void setMessageToRecruiter(String messageToRecruiter) {
        this.messageToRecruiter = messageToRecruiter;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }
}
