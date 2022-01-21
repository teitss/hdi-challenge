package io.github.teitss.testehdi.testehdi.error;

public class InactiveBrokerException extends RuntimeException {

    public InactiveBrokerException() {
        super("Couldn't return requested data, broker is inactive.");
    }
    
}
