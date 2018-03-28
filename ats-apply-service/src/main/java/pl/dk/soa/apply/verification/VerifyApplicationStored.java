package pl.dk.soa.apply.verification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dk.soa.apply.store.ApplicationStoredEvent;

// @Service
// TODO uncomment for verification test
class VerifyApplicationStored {

    private static String VERIFICATION_ENDPOINT = "/v1/verification";

    private final RestTemplate restTemplate;
    private String verificationService;

    VerifyApplicationStored(RestTemplate restTemplate,
                            @Value("${verification-service}") String verificationServiceHost) {
        this.restTemplate = restTemplate;
        this.verificationService = verificationServiceHost + VERIFICATION_ENDPOINT;
    }

    @EventListener(classes = ApplicationStoredEvent.class)
    void onApplicationPersisted(ApplicationStoredEvent event) {
        VerificationResult verificationResult =
                restTemplate.postForObject(verificationService, new ApplicationToVerify(event.getSource()),
                        VerificationResult.class);
        if ("ACCEPTED".equals(verificationResult.getStatus())) {
            event.getSource().accepted();
        } else {
            event.getSource().rejected();
        }
    }

}
