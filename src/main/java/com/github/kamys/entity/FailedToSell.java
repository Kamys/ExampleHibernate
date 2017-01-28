package com.github.kamys.entity;

/**
 * This exception is used when failed to sell the production.
 */
public class FailedToSell extends Exception {
    public FailedToSell() {
    }

    public FailedToSell(String message) {
        super(message);
    }

    public FailedToSell(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToSell(Throwable cause) {
        super(cause);
    }

    public FailedToSell(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
