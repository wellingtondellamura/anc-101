package anc101.io;

/**
 *
 * @author Wellington Della Mura
 */
public class SpreadsheetReaderException extends Exception{

    public SpreadsheetReaderException(String message) {
        this(message, null);
    }

    public SpreadsheetReaderException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
