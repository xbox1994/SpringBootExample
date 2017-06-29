package com.wtytest.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by tywang on 27/06/2017.
 */
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
