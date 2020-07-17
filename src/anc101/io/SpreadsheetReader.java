package anc101.io;

import anc101.SpreadsheetData;
import anc101.SpreadsheetException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Wellington Della Mura
 */
public class SpreadsheetReader {

    private InputStream inputStream;

    public SpreadsheetReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public SpreadsheetData<String> load() throws SpreadsheetReaderException, SpreadsheetException, IOException {
        if (inputStream == null) {
            throw new SpreadsheetReaderException("InputStream must be initialized");
        }

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        Integer n, m;
        n = m = null;
        SpreadsheetData<String> raw = null;
        if (inputReader.ready()) {
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
