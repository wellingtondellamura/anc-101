package anc101.io;

/**
 * Represents an exception when reading data from a spreadsheet
 *
 * @author Wellington Della Mura
 */
public class SpreadsheetReaderException extends Exception {

    /**
     * Constructs a new exception with the specified detail message. 
     *
     * @param message the detail message. 
     */
    public SpreadsheetReaderException(String message) {
        this(message, null);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param  message the detail message.
     * @param  cause the cause.
     */
    public SpreadsheetReaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
