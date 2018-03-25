package pl.dk.soa.apply.domainevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public final class DomainEvents {

    private static ApplicationEventPublisher applicationEventPublisher;

    private DomainEvents() {
    }

    public static void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
        log.trace("Published: {}", event);
    }

    public static void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        DomainEvents.applicationEventPublisher = applicationEventPublisher;
    }
}
