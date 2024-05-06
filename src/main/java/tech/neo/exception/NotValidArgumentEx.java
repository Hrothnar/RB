package tech.neo.exception;

public class NotValidArgumentEx extends Exception {

    public NotValidArgumentEx(String message) {
        super(message);
    }

    public NotValidArgumentEx(String message, Exception exception) {
        super(message, exception);
    }
}
