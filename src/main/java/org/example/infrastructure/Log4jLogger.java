package org.example.infrastructure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLogger {

    private Logger logger;

    public Log4jLogger(Class<?> clazz) {
        logger = LogManager.getLogger(clazz);
    }

    // Métodos para diferentes níveis de log
    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void trace(String message) {
        logger.trace(message);
    }
}
