package anc101;

/**
 * Stores data in a spreadsheet
 *
 * @author Wellington Della Mura
 * @param <T> The type of data stored in this structure
 */
public class SpreadsheetData<T> {

    /**
     * Total number of rows
     */
    private final Integer lines;
    /**
     * Total number of columns
     */
    private final Integer columns;

    /**
     * The stored data
     */
    private Object[][] data;

    /**
     * Creates a new instance of spreadsheet storage
     *
     * @param lines Number of rows
     * @param columns Number of columns
     * @throws SpreadsheetException If there is an incorrect number of rows or
     * columns
     */
    public SpreadsheetData(Integer lines, Integer columns) throws SpreadsheetException {
        this.lines = lines;
        this.columns = columns;
        if (lines > 0 && columns > 0) {
            this.data = new Object[lines][columns];
        } else {
            throw new SpreadsheetException("Invalid number of lines or columns.");
        }
    }

    /**
     * @return The number of columns in the spreadsheet
     */
    public Integer getColumns() {
        return columns;
    }

    /**
     * @return The number of rows in the spreadsheet
     */
    public Integer getLines() {
        return lines;
    }

    /**
     * 
     * @param line The cell row
     * @param column The cell column
     * @return The cell value at the specified position
     * @throws SpreadsheetException In the case of an invalid position
     */
    public T getData(Integer line, Integer column) throws SpreadsheetException {
        checkBounds(line, column);
        return (T) data[line][column];
    }

    /**
     * 
     * @param line The cell row
     * @param column The cell column
     * @param value The value that will be assigned to the cell 
     * @throws SpreadsheetException In the case of an invalid position
     */
    public void setData(Integer line, Integer column, T value) throws SpreadsheetException {
        checkBounds(line, column);
        data[line][column] = value;
    }

    /**
     * Returns the value of an cell based on reference. The cell reference must 
     * be in the format LC where L stands for the row in alphabetically form and
     * C stands for the position of a column. For example: A1
     * @param cellName The cell reference
     * @return The cell value at the specified position
     * @throws SpreadsheetException 
     */
    public T getData(String cellName) throws SpreadsheetException {
        if (cellName == null || cellName.length() < 2) {
            throw new SpreadsheetException(String.format("Invalid Reference: The cell name %s is invalid", cellName));
        }
        cellName = cellName.toUpperCase();
        int line = cellName.charAt(0) - 'A';
        int column = Integer.parseInt(cellName.substring(1)) - 1;
        return getData(line, column);
    }

    /**
     * Checks whether the position is valid in the spreadsheet
     * @param line The cell row
     * @param column The cell column
     * @throws SpreadsheetException In the case of an invalid position
     */
    private void checkBounds(Integer line, Integer column) throws SpreadsheetException {
        if (line >= this.lines || column >= this.columns) {
            throw new SpreadsheetException(String.format("Invalid Reference: The cell on line %d and column %d is out of bounds.", line, column));
        }
    }
}
