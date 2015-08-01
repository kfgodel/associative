package ar.com.kfgodel.associative.identification.api.exceptions;

/**
 * Created by kfgodel on 13/05/15.
 */
public class InterpretationException extends RuntimeException {

    public InterpretationException(String message) {
        super(message);
    }

    public InterpretationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterpretationException(Throwable cause) {
        super(cause);
    }
}
