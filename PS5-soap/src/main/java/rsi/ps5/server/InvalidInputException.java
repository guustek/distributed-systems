package rsi.ps5.server;

public class InvalidInputException extends Exception {

    private final String errorDetails;

    public InvalidInputException(String reason, String errorDetails) {
        super(reason);
        this.errorDetails = errorDetails;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

}
