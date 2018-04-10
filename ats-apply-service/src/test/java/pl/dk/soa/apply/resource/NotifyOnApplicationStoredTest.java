package pl.dk.soa.apply.resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.dk.soa.apply.notification.Notification;
import pl.dk.soa.apply.store.ApplyRepository;
import pl.dk.soa.apply.store.StoredApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.dk.soa.apply.notification.JmsConfig.DESTINATION_NAME;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class NotifyOnApplicationStoredTest {

    @Autowired
    ApplyRepository applyRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Test
    void shouldSendMqMessage() {
        // given
        StoredApplication application = new StoredApplication();
        application.setCandidateId("just_britney");
        application.setListingId("123");
        application.setMessageToRecruiter("eager to work for the whole winter!");

        // when
        applyRepository.store(application);

        // then
        Notification notification = (Notification) jmsTemplate.receiveAndConvert(DESTINATION_NAME);
        assertEquals("just_britney", notification.getCandidateId());
        assertEquals("just_britney@spears.pl", notification.getEmail());
    }

}