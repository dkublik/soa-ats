package pl.dk.soa.apply;

import org.junit.Test;

/**
 *  ats-apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication must be up & running
 *  http://localhost:8080
 */
public class ApplicationVerificationTest {

    /**
     weryfikacja aplikacji nastepuje poprzez komunikacje z nieistniejacym jeszcze serwisem verification-service,
         przez metodke POST,
        na adres /v1/verification
        jako body przekazywana bedzie aplikacja (nazwy pol bez zmian)
        aby uruchomic funkcjonalnosc weryfikacji - zrestartuj serwis ats-apply-service
    */

    @Test
    public void shouldStoreApplicationWithRejectedStatus() throws Exception {
         /** jezeli zaaplikujemy do ats-apply-service,

         z wiadomoscia ktora  pole messageToRecruiter ma rowne "BAD_APPLICATION"
         niezaleznie od innych pol

         to chcemy dostac odpowiedz

         {
            "status": "REJECTED"
         },

         */
    }

    @Test
    public void shouldStoreApplicationWithApproved() throws Exception {
        /** jezeli zaaplikujemy do ats-apply-service ,

         z wiadomoscia ktora w pole messageToRecruiter ma rowne "GOOD_APPLICATION"
         niezaleznie od innych pol

         to dostaniemy odpowiedz

         {
            "status": "ACCEPTED"
         }

         */
    }

}