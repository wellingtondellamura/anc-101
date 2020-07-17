package anc101;

/**
 * Represents an exception when evaluating data from a spreadsheet
 *
 * @author Wellington Della Mura
 */
public class SpreadsheetException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public SpreadsheetException(String message) {
        this(message, null);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause.
     */
    public SpreadsheetException(String message, Throwable cause) {
        super(message, cause);
    }

}
