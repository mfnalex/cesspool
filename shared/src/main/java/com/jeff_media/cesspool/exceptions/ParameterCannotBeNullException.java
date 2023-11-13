package com.jeff_media.cesspool.exceptions;

import com.jeff_media.cesspool.reflection.ReflectionUtils;

/**
 * Represents an exception when null is passed as a parameter to a method that does not allow null parameters.
 */
public class ParameterCannotBeNullException extends IllegalArgumentException {

    /**
     * Creates a new ParameterCannotBeNullException with the given parameter name
     * @param parameterName Name of the parameter that was null
     */
    public ParameterCannotBeNullException(String parameterName) {
        super("Parameter " + parameterName + " cannot be null but was null.");
    }
}
