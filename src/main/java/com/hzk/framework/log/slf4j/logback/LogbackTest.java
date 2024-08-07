package com.hzk.framework.log.slf4j.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {

    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(LogbackTest.class.getName());
        logger.info("logback info");
        logger.error("logback error");

        Logger logger1 = LoggerFactory.getLogger("org.apache.aaa");
        logger1.info("logback info");
        logger1.error("logback error");

    }

}
