package pl.dk.soa.apply;

import org.junit.Test;

/**
 *  ats=apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication must be up & running
 *  http://localhost:8080
 */
public class ApplicationVerificationTest {

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

         z wiadomoscia ktora w pole messageToRecruiter ma rowne "GOOD_APPLICATION"
         niezaleznie od innych pol

         to dostaniemy odpowiedz

         {
            "status": "ACCEPTED"
         }

         */
    }

}