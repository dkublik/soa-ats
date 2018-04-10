package pl.dk.soa.apply.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.dk.soa.apply.store.StoredApplication;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class NotifyOnApplicationStoredTest {

    @Autowired
    MqNotifier notifyOnApplicationStored;

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
        notifyOnApplicationStored.sendNotificationAboutStoredApp(application);

        // then
        Notification notification = (Notification) jmsTemplate.receiveAndConvert(JmsConfig.DESTINATION_NAME);
        assertEquals("just_britney", notification.getCandidateId());
        assertEquals("just_britney@spears.pl", notification.getEmail());
    }

}