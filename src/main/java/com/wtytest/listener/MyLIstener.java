package com.wtytest.listener;

import com.wtytest.event.MyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MyLIstener implements ApplicationListener<MyEvent>{

    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        Logger.getLogger("MyLIstener").info(myEvent.getSource().toString());
    }

}
