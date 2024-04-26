package com.audition.common.logging;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

@Component
public class AuditionLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditionLogger.class);

    public Logger getLogger() {
        return LOGGER;
    }

    public void info(final Logger logger, final String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    public void info(final Logger logger, final String message, final Object object) {
        if (logger.isInfoEnabled()) {
            logger.info(message, object);
        }
    }

    public void debug(final Logger logger, final String message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    public void warn(final Logger logger, final String message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    public void error(final Logger logger, final String message) {
        if (logger.isErrorEnabled()) {
            logger.error(message);
        }
    }

    public void logErrorWithException(final Logger logger, final String message, final Exception e) {
        if (logger.isErrorEnabled()) {
            logger.error(message, e);
        }
    }

    public void logStandardProblemDetail(final Logger logger, final ProblemDetail problemDetail, final Exception e) {
        if (logger.isErrorEnabled()) {
            final var message = createStandardProblemDetailMessage(problemDetail);
            logger.error(message, e);
        }
    }

    public void logHttpStatusCodeError(final Logger logger, final String message, final Integer errorCode) {
        if (logger.isErrorEnabled()) {
            logger.error(createBasicErrorResponseMessage(errorCode, message) + "\n");
        }
    }

    private String createStandardProblemDetailMessage(final ProblemDetail standardProblemDetail) {
        //  Add implementation here.
        if (standardProblemDetail == null) {
            return StringUtils.EMPTY;
        }
        return new StringBuilder("Problem Detail - ")
            .append("Title: ").append(standardProblemDetail.getTitle()).append(", ")
            .append("Status: ").append(standardProblemDetail.getStatus()).append(", ")
            .append("Detail: ").append(standardProblemDetail.getDetail()).append(", ")
            .append("Type: ").append(standardProblemDetail.getType()).append(", ")
            .append("Instance: ").append(standardProblemDetail.getInstance())
            .toString();
    }


    private String createBasicErrorResponseMessage(final Integer errorCode, final String message) {
        //  Add implementation here.
        if (errorCode == null || message == null) {
            return StringUtils.EMPTY;
        }
        return "Error Code: " + errorCode + ", Message: " + message;
    }
}

