package pl.dk.soa.base;

import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import pl.dk.soa.apply.domainevent.DomainEvents;

public class DomainEventsAwareTest {

    @Getter
    ApplicationEvent lastEvent;

    @BeforeEach
    void init() {
        DomainEvents.setApplicationEventPublisher(new ApplicationEventPublisher() {
            @Override
            public void publishEvent(ApplicationEvent event) {
                lastEvent = event;
            }

            @Override
            public void publishEvent(Object event) {
                lastEvent = (ApplicationEvent) event;
            }
        });
    }

}
