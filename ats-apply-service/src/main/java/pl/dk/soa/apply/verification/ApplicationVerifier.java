package pl.dk.soa.apply.verification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dk.soa.apply.store.StoredApplication;

@Service
public class ApplicationVerifier {

    private static String VERIFICATION_ENDPOINT = "/v1/verification";

    private final RestTemplate restTemplate;
    private String verificationService;

    ApplicationVerifier(RestTemplate restTemplate,
                        @Value("${verification-service}") String verificationServiceHost) {
        this.restTemplate = restTemplate;
        this.verificationService = verificationServiceHost + VERIFICATION_ENDPOINT;
    }

    public void verifyByExternalService(StoredApplication application) {
        VerificationResult verificationResult =
                restTemplate.postForObject(verificationService, new ApplicationToVerify(application),
                        VerificationResult.class);
        if ("ACCEPTED".equals(verificationResult.getStatus())) {
            application.accepted();
        } else {
            application.rejected();
        }
    }

}
