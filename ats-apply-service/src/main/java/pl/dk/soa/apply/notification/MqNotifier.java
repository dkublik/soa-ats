package pl.dk.soa.apply.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import pl.dk.soa.apply.store.StoredApplication;

import java.io.IOException;

import static pl.dk.soa.apply.notification.JmsConfig.DESTINATION_NAME;

@Service
public class MqNotifier {

    private static String PREFILL_ENDPOINT = "/v1/prefill/for-candidate/{candidateId}";

    private final JmsTemplate jmsTemplate;
    private final RestTemplate restTemplate;
    private String prefillService;

    MqNotifier(JmsTemplate jmsTemplate, RestTemplate restTemplate,
               @Value("${prefill-service}") String prefillServiceHost) {
        this.jmsTemplate = jmsTemplate;
        this.restTemplate = restTemplate;
        this.prefillService = prefillServiceHost + PREFILL_ENDPOINT;
        restTemplateShouldWorkInTheStubMode();
    }

    public void sendNotificationAboutStoredApp(StoredApplication application) {
        Prefill prefillData = restTemplate.getForObject(prefillService, Prefill.class, application.getCandidateId());
        jmsTemplate.convertAndSend(DESTINATION_NAME, new Notification(application, prefillData));
    }

    private void restTemplateShouldWorkInTheStubMode() {
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                if (response.getStatusCode().value() == 501) {
                    return false;
                }
                return super.hasError(response);
            }
        });
    }

}
