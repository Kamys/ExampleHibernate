package com.github.kamys.entity;

/**
 * Created by HNKNTOC on 26.01.2017.
 * TODO: Add doc.
 */
public class FailedToSell extends Throwable {
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
