package top.kkoishi.Exceptions;

public class ErrorTypeException extends Exception {
    public ErrorTypeException () {
        super("The type selected is error:");
    }

    public ErrorTypeException (String message, Throwable cause) {
        super(message, cause);
    }
}
