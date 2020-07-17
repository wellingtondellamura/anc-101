package anc101.io;

import anc101.SpreadsheetData;
import anc101.SpreadsheetException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Writes the spreadsheet data to the specified output
 * @author Wellington Della Mura
 */
public class SpreadsheetWriter {

    /**
     * The output used to write the spreadsheet data
     */
    private OutputStream outputStream;

    /**
     * Creates a new instance of the class for a given output
     * @param outputStream  The output used to write the spreadsheet data
     */
    public SpreadsheetWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Writes the spreadsheet data to the specified output. The format consists 
     * of the information of the spreadsheet dimension followed by the value of 
     * each cell separated by a new line.
     * After writing, this method closes the output channel.

     * @param data The spreadsheet that will be written on the output
     * @throws IOException If an I/O error occurs while writing to the output
     * @throws SpreadsheetException If there is an invalid position when reading the spreadsheet data
     */
    public void write(SpreadsheetData data) throws IOException, SpreadsheetException {
        BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

        outputWriter.write(String.format("%d %d\n", data.getColumns(), data.getLines()));
        for (int l = 0; l < data.getLines(); l++) {
            for (int c = 0; c < data.getColumns(); c++) {
                String line = String.format("%.5f\n", data.getData(l, c));
                outputWriter.write(line);
            }
        }
        outputWriter.flush();
        outputWriter.close();
    }

}
