package tech.neo.exception;

public class FullStorageEx extends Exception {

    public FullStorageEx(String message) {
        super(message);
    }

    public FullStorageEx(String message, Exception exception) {
        super(message, exception);
    }
}