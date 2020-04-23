package com.tabit.dcm2.exception;

/**
 * This Exception should only been thrown in developing phase, because your code does someting that you should never do.
 * It should be detected by the tests and should never be in production mode, so there is no need for an responsestatus.
 */
public class ProgramException extends RuntimeException {
    public ProgramException(String message) {
        super(message);
    }
}
