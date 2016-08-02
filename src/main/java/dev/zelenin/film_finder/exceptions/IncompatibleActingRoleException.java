package dev.zelenin.film_finder.exceptions;

/**
 * Created by victor on 01.08.16.
 */
public class IncompatibleActingRoleException extends RuntimeException {
    public IncompatibleActingRoleException() {
    }

    public IncompatibleActingRoleException(String message) {
        super(message);
    }

    public IncompatibleActingRoleException(Throwable cause) {
        super(cause);
    }

    public IncompatibleActingRoleException(String message, Throwable cause) {
        super(message, cause);
    }

}
