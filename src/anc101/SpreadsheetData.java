package anc101;

/**
 *
 * @author Wellington Della Mura
 * @param <T> The type of data stored in this structure
 */
public class SpreadsheetData<T> {

    private final Integer lines;
    private final Integer columns;
    private Object[][] data;

    public SpreadsheetData(Integer lines, Integer columns) throws SpreadsheetException {
        this.lines = lines;
        this.columns = columns;
        if (lines > 0 && columns > 0) {
            this.data = new Object[lines][columns];
        } else {
            throw new SpreadsheetException("Invalid number of lines or columns.");
        }
    }

    public T[][] getData() {
        return (T[][]) data;
    }

    public Integer getColumns() {
        return columns;
    }

    public Integer getLines() {
        return lines;
    }

    public T getData(Integer line, Integer column) throws SpreadsheetException{
        checkBounds(line, column);
        return (T) data[line][column];
    }
    
    public void setData(Integer line, Integer column, T value) throws SpreadsheetException{
        checkBounds(line, column);
        data[line][column] = value;
    }

    public T getData(String cellName) throws SpreadsheetException{
        if (cellName==null || cellName.length()<2){
            throw new SpreadsheetException(String.format("Invalid Reference: The cell name %s is invalid", cellName));
        }
        cellName = cellName.toUpperCase();
        int line = cellName.charAt(0) - 'A';
        int column = Integer.parseInt(cellName.substring(1))-1;
        return getData(line, column);
    }
    
    private void checkBounds(Integer line, Integer column) throws SpreadsheetException{
        if (line >= this.lines || column >= this.columns){
            throw new SpreadsheetException(String.format("Invalid Reference: The cell on line %d and column %d is out of bounds.", line, column));
        }
    }
}
