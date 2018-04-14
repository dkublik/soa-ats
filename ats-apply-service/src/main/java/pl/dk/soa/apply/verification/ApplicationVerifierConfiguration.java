package pl.dk.soa.apply.verification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class ApplicationVerifierConfiguration {

    @Bean
    @ConditionalOnProperty(name = "verification.enabled", havingValue = "true")
    ApplicationVerifier applicationVerifier(RestTemplate restTemplate,
            @Value("${verification-service}") String verificationServiceHost) {
        return new ApplicationVerifier(restTemplate, verificationServiceHost);
    }

}
