package com.lqq.springcloud;


import org.apache.logging.log4j.ThreadContext;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Log4j2Test {

    private static final Logger log = LoggerFactory.getLogger(Log4j2Test.class);

    @Test
    public void test(){
        try {
            ThreadContext.put("ROUTINGKEY","a");
            log.info("aaaaaaaaa");
            String s = "aaa,bbb,ccc";
            if (s.contains(",")) {
                String[] split = s.split(",");
                for (String s1 : split) {
                    System.out.println(s1);
                }
            }
            int a = 1 / 0;
        } catch (Exception e) {
            System.out.println("aaaa");
        }

//        DataSource dataSource = new DataSource();
//        dataSource.getNumActive();
//        dataSource.getNumIdle();


        System.out.println("888888");
    }


}
