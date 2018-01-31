package com.wtytest.helper;

import com.wtytest.aop.LogExecutionTime;
import org.springframework.stereotype.Component;

@Component
public class PdfUtils {
    @LogExecutionTime
    public void print(){
        System.out.println("hehe");
    }

    @LogExecutionTime
    public static void p(){
        System.out.println("haha");
    }
}
