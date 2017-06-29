package com.wtytest.helper;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher implements ApplicationEventPublisherAware {

    private static ApplicationEventPublisher PUBLISHER;

    @Override
    public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        PUBLISHER = applicationEventPublisher;
    }

    public static void publish(ApplicationEvent event) {
        PUBLISHER.publishEvent(event);
    }

}
