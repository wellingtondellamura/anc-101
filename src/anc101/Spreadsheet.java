package anc101;

import anc101.io.SpreadsheetReader;
import anc101.io.SpreadsheetReaderException;
import anc101.io.SpreadsheetWriter;
import anc101.parser.SpreadsheetEvaluator;
import java.io.IOException;

/**
 *
 * @author Wellington Della Mura
 */
public class Spreadsheet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SpreadsheetData<String> rawData = new SpreadsheetReader(System.in).load();
            SpreadsheetData<Double> data = new SpreadsheetEvaluator().eval(rawData);
            new SpreadsheetWriter(System.out).write(data);
        } catch (SpreadsheetReaderException ex) {
            System.out.println("There was an error reading the spreadsheet data.\n" + ex.getMessage());
            System.exit(1);
        } catch (SpreadsheetException ex) {
            System.out.println("An error occurred while evaluating the spreadsheet data.\n" + ex.getMessage());
            System.exit(2);
        } catch (IOException ex) {
            System.out.println("An I/O error occurred.\n" + ex.getMessage());
            System.exit(3);
        }
    }
}
