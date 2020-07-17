package anc101.io;

import anc101.SpreadsheetData;
import anc101.SpreadsheetException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Reads spreadsheet data of a specified input stream
 *
 * @author Wellington Della Mura
 */
public class SpreadsheetReader {

    /**
     * The input used to read the spreadsheet data
     */
    private InputStream inputStream;

    /**
     * Creates a new instance of the class for a given input
     *
     * @param inputStream The input used to read the spreadsheet data
     * @throws SpreadsheetReaderException If an null inputStream is informed
     */
    public SpreadsheetReader(InputStream inputStream) throws SpreadsheetReaderException {
        if (inputStream == null) {
            throw new SpreadsheetReaderException("InputStream must be initialized");
        }
        this.inputStream = inputStream;
    }

    /**
     * Loads all data from an entry into a spreadsheet
     *
     * @return The data that has been read
     * @throws SpreadsheetReaderException If an exception occurs when reading
     * data from a spreadsheet
     * @throws SpreadsheetException If an exception occurs when initializing the
     * spreadsheet data
     * @throws IOException If an error occurs while reading the input stream
     */
    public SpreadsheetData<String> load() throws SpreadsheetReaderException, SpreadsheetException, IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        Integer n = null;
        Integer m = null;
        SpreadsheetData<String> raw = null;

        if (inputReader.ready()) {

            //Parses the first line with the spreadsheet dimensions
            String header = inputReader.readLine();
            String[] dimensions = header.split("[ \t]+");
            if (dimensions.length != 2) {
                throw new SpreadsheetReaderException("Invalid dimensions: The values must be two space separated integers greater than 0.");
            }
            try {
                n = Integer.parseInt(dimensions[0]);
                m = Integer.parseInt(dimensions[1]);
            } catch (NumberFormatException e) {
                throw new SpreadsheetReaderException("Invalid dimensions: The values must be two space separated integers greater than 0.", e);
            }
            if (n <= 0 || m <= 0) {
                throw new SpreadsheetReaderException("Invalid dimensions: The values must be two space separated integers greater than 0.");
            }

            //Reads all later rows initializing spreadsheet cells
            raw = new SpreadsheetData<String>(m, n);
            for (int l = 0; l < m; l++) {
                for (int c = 0; c < n; c++) {
                    String line = inputReader.readLine();
                    if (line == null) {
                        throw new SpreadsheetReaderException(String.format("Empty data in cell %d x %d. The spreadsheet data must contains exactly %d lines and %d columns", m, n, m, n));
                    }
                    raw.setData(l, c, line);
                }
            }
        } else {
            throw new SpreadsheetReaderException("InputStream is not ready.");
        }
        inputReader.close();
        return raw;
    }

}
