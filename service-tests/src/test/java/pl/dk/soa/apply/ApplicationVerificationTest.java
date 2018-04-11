package pl.dk.soa.apply;

import org.junit.jupiter.api.Test;

class ApplicationVerificationTest {

    @Test
    public void shouldStoreApplicationWithRejectedStatus() throws Exception {
         /* jezeli uderzamy do ats-apply-service ,

         z wiadomoscia ktora w pole messageToRecruiter ma rowne "BAD_APPLICATION"
         niezaleznie od innych pol

         to dostaniemy odpowiedz

         {
            "status": "REJECTED"
         }

         */
    }

    @Test
    public void shouldStoreApplicationWithApproved() throws Exception {
        /* jezeli uderzamy do ats-apply-service ,

         z wiadomoscia ktora w pole messageToRecruiter ma rowne "any message"
         niezaleznie od innych pol

         to dostaniemy odpowiedz

         {
            "status": "ACCEPTED"
         }

         */
    }

}