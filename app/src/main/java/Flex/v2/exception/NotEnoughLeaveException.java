package Flex.v2.exception;

public class NotEnoughLeaveException extends RuntimeException {
    public NotEnoughLeaveException() {
        super();
    }

    public NotEnoughLeaveException(String message) {
        super(message);
    }

    public NotEnoughLeaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughLeaveException(Throwable cause) {
        super(cause);
    }
}
