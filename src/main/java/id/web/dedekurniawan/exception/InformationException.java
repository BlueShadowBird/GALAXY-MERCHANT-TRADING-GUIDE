package id.web.dedekurniawan.exception;

public class InformationException extends Exception {
    String errorArgument;
    public InformationException() { super(); }
    public InformationException(String message) { super(message); }
    public InformationException(String message, String errorArgument) { super(message); this.errorArgument=errorArgument; }
    public InformationException(String message, Throwable cause) { super(message, cause); }
    public InformationException(Throwable cause) { super(cause); }

    public String getErrorArgument() {
        return errorArgument;
    }
}