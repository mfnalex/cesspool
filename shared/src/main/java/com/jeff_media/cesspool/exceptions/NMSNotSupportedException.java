package com.jeff_media.cesspool.exceptions;

/**
 * Represents an exception when a method is not supported by the current NMS version.
 */
public class NMSNotSupportedException extends RuntimeException {

    /**
     * Creates a new NMSNotSupportedException with the given message
     * @param msg Message
     */
    public NMSNotSupportedException(String msg) {
        super(msg);
    }

}
