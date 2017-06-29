package com.wtytest.listener;

import com.wtytest.entity.User;
import com.wtytest.event.MyEvent;
import com.wtytest.helper.EventPublisher;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import java.util.logging.Logger;

@Component
public class ContextListener{

    @PrePersist
    public void onPostPersist(User user) {
        EventPublisher.publish(new MyEvent(user));
        Logger.getLogger("PrePersist").info("PrePersist invoked!!!!!!!!!!!");
        Logger.getLogger("PrePersist").info("PrePersist invoked!!!!!!!!!!!");
        Logger.getLogger("PrePersist").info("PrePersist invoked!!!!!!!!!!!");
        Logger.getLogger("PrePersist").info("PrePersist invoked!!!!!!!!!!!");
        Logger.getLogger("PrePersist").info("PrePersist invoked!!!!!!!!!!!");
    }
}