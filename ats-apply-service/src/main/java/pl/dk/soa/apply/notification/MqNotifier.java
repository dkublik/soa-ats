package pl.dk.soa.apply.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dk.soa.apply.store.StoredApplication;

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
    }

    public void sendNotificationAboutStoredApp(StoredApplication application) {
        Prefill prefillData = restTemplate.getForObject(prefillService, Prefill.class, application.getCandidateId());
        jmsTemplate.convertAndSend(DESTINATION_NAME, new Notification(application, prefillData));
    }

}
