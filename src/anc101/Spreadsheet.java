package anc101;

import anc101.io.SpreadsheetReader;
import anc101.io.SpreadsheetReaderException;
import anc101.io.SpreadsheetWriter;
import anc101.parser.SpreadsheetEvaluator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        } catch (SpreadsheetException ex) {

        } catch (IOException ex) {

        }

    }

}
