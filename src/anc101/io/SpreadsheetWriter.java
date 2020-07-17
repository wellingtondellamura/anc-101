package anc101.io;

import anc101.SpreadsheetData;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author Wellington Della Mura
 */
public class SpreadsheetWriter {

    private OutputStream outputStream;

    public SpreadsheetWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(SpreadsheetData data) throws IOException {
        BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

        outputWriter.write(String.format("%d %d\n", data.getColumns(), data.getLines()));
        for (int l = 0; l < data.getLines(); l++) {
            for (int c = 0; c < data.getColumns(); c++) {
                String line = String.format("%.5f\n", data.getData()[l][c]);
                outputWriter.write(line);
            }
        }
        outputWriter.flush();
        outputWriter.close();
    }

}
