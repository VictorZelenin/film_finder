package dev.zelenin.film_finder.exceptions;

/**
 * Created by victor on 04.08.16.
 */
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException() {
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(Throwable cause) {
        super(cause);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
