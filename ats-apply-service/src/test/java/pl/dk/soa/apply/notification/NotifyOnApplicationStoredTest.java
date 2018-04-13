package pl.dk.soa.apply.notification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dk.soa.apply.store.StoredApplication;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class NotifyOnApplicationStoredTest {

    @Autowired
    MqNotifier notifyOnApplicationStored;

    @Autowired
    JmsTemplate jmsTemplate;

    @Test
    public void shouldSendMqMessage() {
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