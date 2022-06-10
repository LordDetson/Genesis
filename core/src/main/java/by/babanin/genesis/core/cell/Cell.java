package by.babanin.genesis.core.cell;

import java.util.Objects;

public abstract class Cell implements Comparable<Cell> {

    private final int row;
    private final int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int compareTo(Cell cell) {
        int thisIndexesSum = row + column;
        int indexesSum = cell.row + cell.column;
        int compareValue = thisIndexesSum - indexesSum;
        if(compareValue != 0) {
            return compareValue;
        }
        return row - cell.row;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return row == cell.row && column == cell.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
